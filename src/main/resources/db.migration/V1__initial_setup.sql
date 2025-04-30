CREATE TYPE status AS ENUM ("SCHEDULED","ACCOMPLISHED", "CANCELLED", "RESCHEDULED");

CREATE TABLE "consultations" (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGSERIAL NOT NULL,
    doctor_id BIGSERIAL NOT NULL,
    nurse_id BIGSERIAL NOT NULL,
    status status BIGSERIAL NOT NULL,
    scheduled_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,
    completed_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT fk_patients FOREIGN KEY (patient_id) REFERENCES "patients" (id),
    CONSTRAINT fk_doctors FOREIGN KEY (doctor_id) REFERENCES "doctors" (id),
    CONSTRAINT fk_nurses FOREIGN KEY (nurse_id) REFERENCES "nurses" (id)

);

CREATE TABLE "diagnostics" (
    id BIGSERIAL PRIMARY KEY,
    consultation_id BIGSERIAL NOT NULL,
    patient_id BIGSERIAL NOT NULL,
    doctor_id BIGSERIAL NOT NULL,
    symptoms_id BIGSERIAL NOT NULL,
    prescriptions_details_id BIGSERIAL NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_consultations FOREIGN KEY (consultation_id) REFERENCES "consultations" (id),
    CONSTRAINT fk_patients FOREIGN KEY (patient_id) REFERENCES "patients" (id),
    CONSTRAINT fk_doctors FOREIGN KEY (doctor_id) REFERENCES "doctors" (id),
    CONSTRAINT fk_symptoms FOREIGN KEY (symptoms_id) REFERENCES "symptoms" (id)

);

CREATE TYPE specialty AS ENUM ("    ALLERGY_AND_IMMUNOLOGY",
                                    "ANESTHESIOLOGY",
                                    "CARDIOLOGY",
                                    "DERMATOLOGY",
                                    "EMERGENCY_MEDICINE",
                                    "ENDOCRINOLOGY",
                                    "FAMILY_MEDICINE",
                                    "GASTROENTEROLOGY",
                                    "GENERAL_SURGERY",
                                    "GERIATRICS",
                                    "HEMATOLOGY",
                                    "INFECTIOUS_DISEASES",
                                    "INTERNAL_MEDICINE",
                                    "NEPHROLOGY",
                                    "NEUROLOGY",
                                    "OBSTETRICS_AND_GYNECOLOGY",
                                    "ONCOLOGY",
                                    "OPHTHALMOLOGY",
                                    "ORTHOPEDICS",
                                    "OTOLARYNGOLOGY",
                                    "PEDIATRICS",
                                    "PLASTIC_SURGERY",
                                    "PSYCHIATRY",
                                    "PULMONOLOGY",
                                    "RHEUMATOLOGY",
                                    "UROLOGY");

CREATE TABLE "doctors" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    phone_id BIGSERIAL NOT NULL,
    email VARCHAR(100) NOT NULL,
    specialty specialty NOT NULL,
    crm VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);

CREATE TYPE relationship_type AS ENUM ("    BROTHER",
                                            "CHILD",
                                            "FATHER",
                                            "FRIEND",
                                            "GRANDFATHER",
                                            "GRANDMOTHER",
                                            "MOTHER",
                                            "PARTNER",
                                            "SISTER",
                                            "SPOUSE",
                                            "UNCLE",
                                            "AUNT",
                                            "COUSIN",
                                            "GUARDIAN",
                                            "NEIGHBOR",
                                            "OTHER");

CREATE TABLE "emergency_contact" (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGSERIAL NOT NULL,
    contact_name VARCHAR(255) NOT NULL,
    contact_phone_id BIGSERIAL NOT NULL,
    relationship_type relationship_type NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_patients FOREIGN KEY (patient_id) REFERENCES "patients" (id),
    CONSTRAINT fk_phone FOREIGN KEY (contact_phone_id) REFERENCES "phone" (id)

);

CREATE TYPE area_of_work AS ENUM ("     ANESTHESIA",
                                        "CARDIAC",
                                        "COMMUNITY_HEALTH",
                                        "CRITICAL_CARE",
                                        'DIALYSIS',
                                        "EMERGENCY",
                                        "GERIATRIC",
                                        "HOSPICE_AND_PALLIATIVE_CARE",
                                        "HOME_HEALTH",
                                        "INFECTIOUS_DISEASES",
                                        "NEONATAL",
                                        "OCCUPATIONAL_HEALTH",
                                        "ONCOLOGY",
                                        "PEDIATRIC",
                                        "PSYCHIATRIC",
                                        "PUBLIC_HEALTH",
                                        "REHABILITATION",
                                        "SCHOOL_NURSING",
                                        "SURGICAL",
                                        "TELEHEALTH");

CREATE TABLE "nurses" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    phone_id BIGSERIAL NOT NULL,
    area_of_work area_of_work NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

   CONSTRAINT fk_phone FOREIGN KEY (contact_phone_id) REFERENCES "phone" (id)

);

CREATE TABLE "patients" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    emergency_contact_id BIGSERIAL NOT NULL,
    phone_id BIGSERIAL NOT NULL,
    has_allergies BOOLEAN DEFAULT FALSE NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_emergency_contact FOREIGN KEY (emergency_contact_id) REFERENCES "emergency_contact" (id)
    CONSTRAINT fk_phone FOREIGN KEY (phone_id) REFERENCES "phone" (id)
);

CREATE TYPE phone_type AS ENUM (" CELL_PHONE", "TELEPHONE", "BUSINESS_PHONE");

CREATE TABLE "phone" (
    id BIGSERIAL PRIMARY KEY,
    area_code VARCHAR(3) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    phone_type phone_type NOT NULL,
    operator VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "prescription_details" (
    id BIGSERIAL PRIMARY KEY,
    medication_name VARCHAR(255) NOT NULL,
    dosage VARCHAR(255) NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    administration_form VARCHAR(50) NOT NULL,
    instructions VARCHAR(255),
    follow_up_date TIMESTAMPTZ DEFAULT current_timestamp,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE "symptoms" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);
