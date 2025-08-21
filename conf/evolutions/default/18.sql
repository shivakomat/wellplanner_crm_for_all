# --- !Ups

CREATE TABLE intake_forms (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description CLOB,
    form_schema CLOB NOT NULL,
    public_id VARCHAR(36) NOT NULL,
    status VARCHAR(20) DEFAULT 'DRAFT',
    created_at VARCHAR(50),
    updated_at VARCHAR(50),
    FOREIGN KEY (business_id) REFERENCES businesses(id)
);

CREATE UNIQUE INDEX idx_intake_forms_public_id ON intake_forms(public_id);
CREATE INDEX idx_intake_forms_business_id ON intake_forms(business_id);
CREATE INDEX idx_intake_forms_status ON intake_forms(status);

CREATE TABLE form_responses (
    id SERIAL PRIMARY KEY,
    form_id BIGINT NOT NULL,
    response_data CLOB NOT NULL,
    submitter_email VARCHAR(255),
    submitter_name VARCHAR(255),
    submitted_at VARCHAR(50),
    ip_address VARCHAR(45),
    user_agent CLOB,
    FOREIGN KEY (form_id) REFERENCES intake_forms(id)
);

CREATE INDEX idx_form_responses_form_id ON form_responses(form_id);
CREATE INDEX idx_form_responses_submitted_at ON form_responses(submitted_at);
CREATE INDEX idx_form_responses_submitter_email ON form_responses(submitter_email);

# --- !Downs

DROP TABLE IF EXISTS form_responses;
DROP TABLE IF EXISTS intake_forms;
