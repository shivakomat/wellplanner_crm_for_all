# --- !Ups

CREATE TABLE payment_methods (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    stripe_payment_method_id VARCHAR(255) NOT NULL,
    type ENUM('card', 'bank_account') NOT NULL,
    last4 VARCHAR(4),
    brand VARCHAR(50),
    exp_month INT,
    exp_year INT,
    is_default BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    INDEX idx_business_id (business_id),
    INDEX idx_stripe_payment_method_id (stripe_payment_method_id)
);

CREATE TABLE payment_intents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    stripe_payment_intent_id VARCHAR(255) NOT NULL UNIQUE,
    amount BIGINT NOT NULL, -- Amount in cents
    currency VARCHAR(3) DEFAULT 'USD',
    status ENUM('requires_payment_method', 'requires_confirmation', 'requires_action', 'processing', 'succeeded', 'canceled') NOT NULL,
    description TEXT,
    customer_email VARCHAR(255),
    customer_name VARCHAR(255),
    metadata JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    INDEX idx_business_id (business_id),
    INDEX idx_stripe_payment_intent_id (stripe_payment_intent_id),
    INDEX idx_status (status)
);

CREATE TABLE transactions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    payment_intent_id BIGINT,
    stripe_charge_id VARCHAR(255),
    amount BIGINT NOT NULL, -- Amount in cents
    fee BIGINT, -- Stripe fee in cents
    net_amount BIGINT, -- Amount minus fees
    currency VARCHAR(3) DEFAULT 'USD',
    status ENUM('pending', 'succeeded', 'failed', 'refunded', 'partially_refunded') NOT NULL,
    description TEXT,
    customer_email VARCHAR(255),
    customer_name VARCHAR(255),
    receipt_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    FOREIGN KEY (payment_intent_id) REFERENCES payment_intents(id),
    INDEX idx_business_id (business_id),
    INDEX idx_payment_intent_id (payment_intent_id),
    INDEX idx_stripe_charge_id (stripe_charge_id),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at)
);

CREATE TABLE subscription_plans (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    amount BIGINT NOT NULL, -- Amount in cents
    currency VARCHAR(3) DEFAULT 'USD',
    interval_type ENUM('day', 'week', 'month', 'year') NOT NULL,
    interval_count INT DEFAULT 1,
    stripe_price_id VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    INDEX idx_business_id (business_id),
    INDEX idx_stripe_price_id (stripe_price_id),
    INDEX idx_is_active (is_active)
);

CREATE TABLE subscriptions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255),
    plan_id BIGINT NOT NULL,
    stripe_subscription_id VARCHAR(255),
    stripe_customer_id VARCHAR(255),
    status ENUM('active', 'past_due', 'canceled', 'unpaid', 'trialing') NOT NULL,
    current_period_start TIMESTAMP,
    current_period_end TIMESTAMP,
    trial_end TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES subscription_plans(id),
    INDEX idx_business_id (business_id),
    INDEX idx_customer_email (customer_email),
    INDEX idx_plan_id (plan_id),
    INDEX idx_stripe_subscription_id (stripe_subscription_id),
    INDEX idx_status (status)
);

# --- !Downs

DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS subscription_plans;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS payment_intents;
DROP TABLE IF EXISTS payment_methods;
