# --- !Ups

CREATE TABLE payment_methods (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    stripe_payment_method_id VARCHAR(255) NOT NULL,
    type VARCHAR(20) NOT NULL,
    last4 VARCHAR(4),
    brand VARCHAR(50),
    exp_month INT,
    exp_year INT,
    is_default BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id)
);

CREATE INDEX idx_payment_methods_business_id ON payment_methods(business_id);
CREATE INDEX idx_payment_methods_stripe_id ON payment_methods(stripe_payment_method_id);

CREATE TABLE payment_intents (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    stripe_payment_intent_id VARCHAR(255) NOT NULL,
    amount BIGINT NOT NULL, -- Amount in cents
    currency VARCHAR(3) DEFAULT 'USD',
    status VARCHAR(50) NOT NULL,
    description CLOB,
    customer_email VARCHAR(255),
    customer_name VARCHAR(255),
    metadata CLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id)
);

CREATE UNIQUE INDEX idx_payment_intents_stripe_id ON payment_intents(stripe_payment_intent_id);
CREATE INDEX idx_payment_intents_business_id ON payment_intents(business_id);
CREATE INDEX idx_payment_intents_status ON payment_intents(status);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    payment_intent_id BIGINT,
    stripe_charge_id VARCHAR(255),
    amount BIGINT NOT NULL, -- Amount in cents
    fee BIGINT, -- Stripe fee in cents
    net_amount BIGINT, -- Amount minus fees
    currency VARCHAR(3) DEFAULT 'USD',
    status VARCHAR(50) NOT NULL,
    description CLOB,
    customer_email VARCHAR(255),
    customer_name VARCHAR(255),
    receipt_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id),
    FOREIGN KEY (payment_intent_id) REFERENCES payment_intents(id)
);

CREATE INDEX idx_transactions_business_id ON transactions(business_id);
CREATE INDEX idx_transactions_payment_intent_id ON transactions(payment_intent_id);
CREATE INDEX idx_transactions_stripe_charge_id ON transactions(stripe_charge_id);
CREATE INDEX idx_transactions_status ON transactions(status);
CREATE INDEX idx_transactions_created_at ON transactions(created_at);

CREATE TABLE subscription_plans (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description CLOB,
    amount BIGINT NOT NULL, -- Amount in cents
    currency VARCHAR(3) DEFAULT 'USD',
    interval_type VARCHAR(20) NOT NULL,
    interval_count INT DEFAULT 1,
    stripe_price_id VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id)
);

CREATE INDEX idx_subscription_plans_business_id ON subscription_plans(business_id);
CREATE INDEX idx_subscription_plans_stripe_price_id ON subscription_plans(stripe_price_id);
CREATE INDEX idx_subscription_plans_is_active ON subscription_plans(is_active);

CREATE TABLE subscriptions (
    id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255),
    plan_id BIGINT NOT NULL,
    stripe_subscription_id VARCHAR(255),
    stripe_customer_id VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    current_period_start TIMESTAMP,
    current_period_end TIMESTAMP,
    trial_end TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (business_id) REFERENCES businesses(id),
    FOREIGN KEY (plan_id) REFERENCES subscription_plans(id)
);

CREATE INDEX idx_subscriptions_business_id ON subscriptions(business_id);
CREATE INDEX idx_subscriptions_customer_email ON subscriptions(customer_email);
CREATE INDEX idx_subscriptions_plan_id ON subscriptions(plan_id);
CREATE INDEX idx_subscriptions_stripe_subscription_id ON subscriptions(stripe_subscription_id);
CREATE INDEX idx_subscriptions_status ON subscriptions(status);

# --- !Downs

DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS subscription_plans;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS payment_intents;
DROP TABLE IF EXISTS payment_methods;
