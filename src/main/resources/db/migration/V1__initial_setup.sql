CREATE TYPE phone_type AS ENUM ('CELL_PHONE', 'TELEPHONE', 'BUSINESS_PHONE');

CREATE TYPE status AS ENUM ('SCHEDULED', 'ACCOMPLISHED', 'CANCELLED', 'RESCHEDULED');

CREATE TYPE specialty AS ENUM (
    'ALLERGY_AND_IMMUNOLOGY',
    'ANESTHESIOLOGY',
    'CARDIOLOGY',
    'DERMATOLOGY',
    'EMERGENCY_MEDICINE',
    'ENDOCRINOLOGY',
    'FAMILY_MEDICINE',
    'GASTROENTEROLOGY',
    'GENERAL_SURGERY',
    'GERIATRICS',
    'HEMATOLOGY',
    'INFECTIOUS_DISEASES',
    'INTERNAL_MEDICINE',
    'NEPHROLOGY',
    'NEUROLOGY',
    'OBSTETRICS_AND_GYNECOLOGY',
    'ONCOLOGY',
    'OPHTHALMOLOGY',
    'ORTHOPEDICS',
    'OTOLARYNGOLOGY',
    'PEDIATRICS',
    'PLASTIC_SURGERY',
    'PSYCHIATRY',
    'PULMONOLOGY',
    'RHEUMATOLOGY',
    'UROLOGY'
);

CREATE TYPE relationship_type AS ENUM (
    'BROTHER',
    'CHILD',
    'FATHER',
    'FRIEND',
    'GRANDFATHER',
    'GRANDMOTHER',
    'MOTHER',
    'PARTNER',
    'SISTER',
    'SPOUSE',
    'UNCLE',
    'AUNT',
    'COUSIN',
    'GUARDIAN',
    'NEIGHBOR',
    'OTHER'
);

CREATE TYPE area_of_work AS ENUM (
    'ANESTHESIA',
    'CARDIAC',
    'COMMUNITY_HEALTH',
    'CRITICAL_CARE',
    'DIALYSIS',
    'EMERGENCY',
    'GERIATRIC',
    'HOSPICE_AND_PALLIATIVE_CARE',
    'HOME_HEALTH',
    'INFECTIOUS_DISEASES',
    'NEONATAL',
    'OCCUPATIONAL_HEALTH',
    'ONCOLOGY',
    'PEDIATRIC',
    'PSYCHIATRIC',
    'PUBLIC_HEALTH',
    'REHABILITATION',
    'SCHOOL_NURSING',
    'SURGICAL',
    'TELEHEALTH'
);

CREATE TYPE user_type AS ENUM ('DOCTOR', 'NURSE', 'PATIENT');

CREATE TABLE tb_user (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(254) NOT NULL,
    user_type user_type NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);

CREATE TABLE phone (
    id BIGSERIAL PRIMARY KEY,
    area_code VARCHAR(3) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    phone_type phone_type NOT NULL,
    operator VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp
);

CREATE TABLE emergency_contact (
    id BIGSERIAL PRIMARY KEY,
    contact_name VARCHAR(255) NOT NULL,
    phone_id BIGINT NOT NULL,
    relationship_type relationship_type NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_emergency_phone FOREIGN KEY (phone_id) REFERENCES phone(id)
);

CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    emergency_contact_id BIGINT NOT NULL,
    has_allergies BOOLEAN NOT NULL DEFAULT FALSE,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_patient_user FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_patient_phone FOREIGN KEY (phone_id) REFERENCES phone(id),
    CONSTRAINT fk_patient_emergency_contact FOREIGN KEY (emergency_contact_id) REFERENCES emergency_contact(id)
);

CREATE TABLE doctor (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    specialty specialty NOT NULL,
    crm VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_doctor_user FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_doctor_phone FOREIGN KEY (phone_id) REFERENCES phone(id)
);

CREATE TABLE nurse (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    area_of_work area_of_work NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_nurse_user FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_nurse_phone FOREIGN KEY (phone_id) REFERENCES phone(id)
);

CREATE TABLE consultation (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    nurse_id BIGINT,
    status status NOT NULL,
    scheduled_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    completed_at TIMESTAMPTZ,
    CONSTRAINT fk_consultation_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_consultation_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    CONSTRAINT fk_consultation_nurse FOREIGN KEY (nurse_id) REFERENCES nurse(id)
);

CREATE TABLE diagnostic (
    id BIGSERIAL PRIMARY KEY,
    consultation_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_diagnostic_consultation FOREIGN KEY (consultation_id) REFERENCES consultation(id),
    CONSTRAINT fk_diagnostic_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_diagnostic_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE TABLE symptom (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    diagnostic_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_symptom_diagnostic FOREIGN KEY (diagnostic_id) REFERENCES diagnostic(id)
);

CREATE TABLE prescription_details (
    id BIGSERIAL PRIMARY KEY,
    diagnostic_id BIGINT NOT NULL,
    medication_name VARCHAR(255) NOT NULL,
    dosage VARCHAR(255) NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    route_of_administration VARCHAR(50) NOT NULL,
    instructions VARCHAR(255),
    follow_up_date TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_prescription_diagnostic FOREIGN KEY (diagnostic_id) REFERENCES diagnostic(id)
);

CREATE INDEX idx_consultation_patient_id ON consultation(patient_id);
CREATE INDEX idx_consultation_scheduled_at ON consultation(scheduled_at);
CREATE INDEX idx_consultation_status ON consultation(status);

CREATE OR REPLACE FUNCTION get_patient_consultation_history(p_patient_id BIGINT)
RETURNS TABLE (
    consultation_id BIGINT,
    doctor_id BIGINT,
    nurse_id BIGINT,
    status status,
    scheduled_at TIMESTAMPTZ,
    completed_at TIMESTAMPTZ
) AS $$
BEGIN
    RETURN QUERY
    SELECT c.id, c.doctor_id, c.nurse_id, c.status, c.scheduled_at, c.completed_at
    FROM consultation c
    WHERE c.patient_id = p_patient_id
    ORDER BY c.scheduled_at DESC;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_future_consultations(p_patient_id BIGINT)
RETURNS TABLE (
    consultation_id BIGINT,
    doctor_id BIGINT,
    nurse_id BIGINT,
    status status,
    scheduled_at TIMESTAMPTZ
) AS $$
BEGIN
    RETURN QUERY
    SELECT c.id, c.doctor_id, c.nurse_id, c.status, c.scheduled_at
    FROM consultation c
    WHERE c.patient_id = p_patient_id AND c.scheduled_at > now()
    ORDER BY c.scheduled_at;
END;
$$ LANGUAGE plpgsql;
