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
