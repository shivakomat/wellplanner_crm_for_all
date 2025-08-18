# --- !Ups

CREATE TABLE intake_forms (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    form_schema JSON NOT NULL,
    public_id VARCHAR(36) NOT NULL UNIQUE,
    status ENUM('DRAFT', 'ACTIVE', 'INACTIVE') DEFAULT 'DRAFT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    INDEX idx_business_id (business_id),
    INDEX idx_public_id (public_id),
    INDEX idx_status (status)
);

CREATE TABLE form_responses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    form_id BIGINT NOT NULL,
    response_data JSON NOT NULL,
    submitter_email VARCHAR(255),
    submitter_name VARCHAR(255),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    user_agent TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (form_id) REFERENCES intake_forms(id) ON DELETE CASCADE,
    INDEX idx_form_id (form_id),
    INDEX idx_submitted_at (submitted_at),
    INDEX idx_submitter_email (submitter_email)
);

# --- !Downs

DROP TABLE IF EXISTS form_responses;
DROP TABLE IF EXISTS intake_forms;
