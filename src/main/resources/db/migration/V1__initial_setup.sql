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

CREATE TABLE "phone" (
    id BIGSERIAL PRIMARY KEY,
    area_code VARCHAR(3) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    phone_type phone_type NOT NULL,
    operator VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL
);

CREATE TABLE "patient" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    has_allergies BOOLEAN DEFAULT FALSE,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);

CREATE TABLE "doctor" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    specialty specialty NOT NULL,
    crm VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);

CREATE TABLE "nurse" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_id BIGINT NOT NULL,
    area_of_work area_of_work NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);

CREATE TABLE "consultation" (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    nurse_id BIGINT NOT NULL,
    status status NOT NULL,
    scheduled_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    completed_at TIMESTAMPTZ,
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES "patient" (id),
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES "doctor" (id),
    CONSTRAINT fk_nurse FOREIGN KEY (nurse_id) REFERENCES "nurse" (id)
);

CREATE TABLE "symptom" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL
);

CREATE TABLE "diagnostic" (
    id BIGSERIAL PRIMARY KEY,
    consultation_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    symptom_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_consultation FOREIGN KEY (consultation_id) REFERENCES "consultation" (id),
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES "patient" (id),
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES "doctor" (id),
    CONSTRAINT fk_symptom FOREIGN KEY (symptom_id) REFERENCES "symptom" (id)
);

CREATE TABLE "prescription_details" (
    id BIGSERIAL PRIMARY KEY,
    diagnostic_id BIGINT NOT NULL,
    medication_name VARCHAR(255) NOT NULL,
    dosage VARCHAR(255) NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    route_of_administration VARCHAR(50) NOT NULL,
    instructions VARCHAR(255),
    follow_up_date TIMESTAMPTZ DEFAULT current_timestamp,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_diagnostic FOREIGN KEY (diagnostic_id) REFERENCES "diagnostic" (id)
);

CREATE TABLE "emergency_contact" (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    contact_name VARCHAR(255) NOT NULL,
    contact_phone_id BIGINT NOT NULL,
    relationship_type relationship_type NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES "patient" (id),
    CONSTRAINT fk_phone FOREIGN KEY (contact_phone_id) REFERENCES "phone" (id)
);

CREATE TABLE "phone_relationships" (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT,
    doctor_id BIGINT,
    nurse_id BIGINT,
    phone_id BIGINT,
    CONSTRAINT fk_patient_phone FOREIGN KEY (patient_id) REFERENCES "patient" (id),
    CONSTRAINT fk_doctor_phone FOREIGN KEY (doctor_id) REFERENCES "doctor" (id),
    CONSTRAINT fk_nurse_phone FOREIGN KEY (nurse_id) REFERENCES "nurse" (id),
    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);
