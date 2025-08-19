# Payment Gateway Integration Documentation

## Overview
This document outlines the payment gateway integration options and implementation strategy for the WellPlanner CRM. The payment system will enable clients to collect payments through forms, invoices, and subscription services.

## Payment Gateway Options Analysis

### 1. Stripe ⭐ **RECOMMENDED**
**Why Stripe is the best choice for WellPlanner CRM:**

#### Advantages
- **Excellent Developer Experience**: Comprehensive APIs with great documentation
- **Strong Scala/Play Framework Support**: Official libraries and community resources
- **Transparent Pricing**: 2.9% + 30¢ per successful transaction
- **Built-in Security**: PCI compliance handled automatically, fraud protection included
- **Comprehensive Features**: One-time payments, subscriptions, marketplaces, refunds
- **Reliable Infrastructure**: 99.99% uptime, handles billions in transactions
- **International Support**: 40+ countries, 135+ currencies
- **Developer Tools**: Test mode, webhooks, detailed analytics

#### Pricing Structure
- **Standard Rate**: 2.9% + 30¢ per successful transaction
- **International Cards**: Additional 1.5% fee
- **Subscriptions**: Same rate as one-time payments
- **No Monthly Fees**: Pay only for successful transactions
- **No Setup Fees**: Free to get started

#### Integration Complexity
**Rating: ⭐⭐⭐⭐⭐ (Excellent)**
- Well-documented REST APIs
- Official Scala/Java libraries
- Extensive code examples and tutorials
- Active community support

### 2. PayPal
#### Advantages
- Widely recognized brand trust
- Good international coverage
- Multiple integration options
- Competitive fees for certain transaction types

#### Disadvantages
- More complex API compared to Stripe
- Inconsistent developer experience
- Account holds and restrictions can be problematic
- Less flexible for complex payment scenarios

#### Integration Complexity
**Rating: ⭐⭐⭐ (Moderate)**

### 3. Square
#### Advantages
- Great for small businesses
- Competitive pricing structure
- Strong mobile payment support
- Integrated POS solutions

#### Disadvantages
- Limited international support
- Less flexible than Stripe for complex scenarios
- Fewer advanced features

#### Integration Complexity
**Rating: ⭐⭐⭐⭐ (Good)**

### 4. Authorize.Net
#### Advantages
- Long-established provider
- Good enterprise features
- Competitive rates for high volume

#### Disadvantages
- Older API design patterns
- More complex setup process
- Limited modern payment features

#### Integration Complexity
**Rating: ⭐⭐ (Complex)**

## Recommended Architecture: Stripe Integration

### Core System Components

#### 1. Database Schema
```sql
-- Payment methods (stored cards, bank accounts)
CREATE TABLE payment_methods (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    stripe_payment_method_id VARCHAR(255) NOT NULL,
    type ENUM('card', 'bank_account') NOT NULL,
    last4 VARCHAR(4),
    brand VARCHAR(50),
    is_default BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE
);

-- Payment intents (pending/processing payments)
CREATE TABLE payment_intents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    stripe_payment_intent_id VARCHAR(255) NOT NULL UNIQUE,
    amount BIGINT NOT NULL, -- Amount in cents
    currency VARCHAR(3) DEFAULT 'USD',
    status ENUM('requires_payment_method', 'requires_confirmation', 'requires_action', 'processing', 'succeeded', 'canceled') NOT NULL,
    metadata JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE
);

-- Completed transactions
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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    FOREIGN KEY (payment_intent_id) REFERENCES payment_intents(id)
);

-- Subscription plans
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
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE
);

-- Customer subscriptions
CREATE TABLE subscriptions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    business_id BIGINT NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    plan_id BIGINT NOT NULL,
    stripe_subscription_id VARCHAR(255),
    status ENUM('active', 'past_due', 'canceled', 'unpaid') NOT NULL,
    current_period_start TIMESTAMP,
    current_period_end TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES subscription_plans(id)
);
```

#### 2. Backend Services Architecture
```scala
// Core payment processing
trait PaymentService {
  def createPaymentIntent(amount: Long, currency: String, businessId: Long): Future[PaymentIntent]
  def confirmPayment(paymentIntentId: String): Future[PaymentResult]
  def refundPayment(chargeId: String, amount: Option[Long]): Future[RefundResult]
  def getPaymentHistory(businessId: Long): Future[List[Transaction]]
}

// Stripe API wrapper
trait StripeService {
  def createCustomer(email: String, name: Option[String]): Future[Customer]
  def createPaymentMethod(customerId: String, paymentMethodId: String): Future[PaymentMethod]
  def createSubscription(customerId: String, priceId: String): Future[Subscription]
  def handleWebhook(payload: String, signature: String): Future[WebhookEvent]
}

// Subscription management
trait SubscriptionService {
  def createPlan(businessId: Long, planDetails: CreatePlanRequest): Future[SubscriptionPlan]
  def subscribeToPlan(businessId: Long, planId: Long, customerEmail: String): Future[Subscription]
  def cancelSubscription(subscriptionId: Long): Future[Boolean]
  def updateSubscription(subscriptionId: Long, newPlanId: Long): Future[Subscription]
}
```

#### 3. API Endpoints Structure
```
# Payment Processing
POST   /businesses/:id/payments/intents          # Create payment intent
POST   /businesses/:id/payments/confirm          # Confirm payment
GET    /businesses/:id/payments/history          # Payment history
POST   /businesses/:id/payments/refund           # Process refund

# Payment Methods Management
POST   /businesses/:id/payment-methods           # Add payment method
GET    /businesses/:id/payment-methods           # List payment methods
DELETE /businesses/:id/payment-methods/:id       # Remove payment method
PUT    /businesses/:id/payment-methods/:id/default # Set default payment method

# Subscription Management
POST   /businesses/:id/subscription-plans        # Create subscription plan
GET    /businesses/:id/subscription-plans        # List subscription plans
PUT    /businesses/:id/subscription-plans/:id    # Update subscription plan
POST   /businesses/:id/subscriptions             # Create subscription
GET    /businesses/:id/subscriptions             # List subscriptions
PUT    /businesses/:id/subscriptions/:id         # Update subscription
DELETE /businesses/:id/subscriptions/:id         # Cancel subscription

# Public Payment Endpoints
GET    /payments/checkout/:paymentIntentId       # Public checkout page
POST   /payments/process                         # Process public payment

# Webhook Handlers
POST   /webhooks/stripe                          # Stripe webhook handler
```

## Integration with Existing Features

### 1. Invoice Payment Integration
- **Pay Now Buttons**: Add payment buttons to existing invoices
- **Payment Links**: Generate secure payment URLs for email invoices
- **Status Tracking**: Update invoice status when payments are received
- **Partial Payments**: Support for partial invoice payments

### 2. Form Payment Integration
- **Payment Fields**: Add payment collection to intake forms
- **Checkout Flow**: Seamless payment during form submission
- **Recurring Payments**: Support for subscription-based form submissions
- **Payment Confirmation**: Automated confirmation emails

### 3. Client Portal Enhancement
- **Payment History**: Clients can view their payment history
- **Saved Payment Methods**: Store and manage payment methods securely
- **Subscription Management**: Self-service subscription changes
- **Receipt Downloads**: PDF receipt generation

## Implementation Phases

### Phase 1: Core Payment Infrastructure (Week 1-2)
**Objectives:**
- Set up Stripe account and API integration
- Implement basic database schema
- Create core payment services
- Build webhook handling system

**Deliverables:**
- Payment database tables and models
- Basic PaymentService implementation
- Stripe API integration wrapper
- Webhook endpoint for payment status updates

### Phase 2: Invoice Payment Integration (Week 3)
**Objectives:**
- Add payment functionality to existing invoices
- Create payment links for email invoices
- Implement payment status tracking

**Deliverables:**
- "Pay Now" buttons on invoice pages
- Email invoice templates with payment links
- Invoice payment status updates
- Payment confirmation notifications

### Phase 3: Form Payment Integration (Week 4)
**Objectives:**
- Extend intake forms with payment fields
- Create checkout flow for form submissions
- Implement payment confirmation workflows

**Deliverables:**
- Payment field types for forms
- Checkout page for form payments
- Payment confirmation emails
- Form submission with payment tracking

### Phase 4: Advanced Features (Week 5-6)
**Objectives:**
- Implement subscription management
- Add payment analytics and reporting
- Create client portal payment features

**Deliverables:**
- Subscription plan management
- Recurring payment processing
- Payment analytics dashboard
- Client portal payment history

## Technical Implementation Details

### Dependencies (build.sbt)
```scala
libraryDependencies ++= Seq(
  "com.stripe" % "stripe-java" % "22.30.0",
  "com.typesafe.play" %% "play-json" % "2.9.4",
  "com.typesafe.play" %% "play-ws" % "2.8.19"
)
```

### Configuration (application.conf)
```hocon
# Stripe Configuration
stripe {
  publishableKey = ${?STRIPE_PUBLISHABLE_KEY}
  secretKey = ${?STRIPE_SECRET_KEY}
  webhookSecret = ${?STRIPE_WEBHOOK_SECRET}
  apiVersion = "2023-10-16"
  baseUrl = "https://api.stripe.com"
}

# Payment Configuration
payments {
  defaultCurrency = "USD"
  allowedCurrencies = ["USD", "EUR", "GBP", "CAD"]
  maxPaymentAmount = 100000 # $1000.00 in cents
  minPaymentAmount = 50     # $0.50 in cents
}
```

### Environment Variables
```bash
# Required Stripe Configuration
export STRIPE_PUBLISHABLE_KEY="pk_test_..."
export STRIPE_SECRET_KEY="sk_test_..."
export STRIPE_WEBHOOK_SECRET="whsec_..."

# Optional Configuration
export PAYMENT_DEFAULT_CURRENCY="USD"
export PAYMENT_MAX_AMOUNT="100000"
```

## Security and Compliance

### PCI Compliance Strategy
- **Stripe Elements**: Use Stripe's secure card collection forms
- **No Card Storage**: Never store raw card data in application database
- **Tokenization**: Use Stripe's secure tokenization system
- **HTTPS Only**: All payment-related endpoints require HTTPS

### Security Best Practices
- **Webhook Verification**: Verify all webhook signatures from Stripe
- **API Key Management**: Separate test and production keys, regular rotation
- **Transaction Logging**: Comprehensive audit trails for all payment activities
- **Fraud Detection**: Leverage Stripe's built-in fraud protection
- **Rate Limiting**: Implement rate limiting on payment endpoints

### Data Protection
- **Encryption**: Encrypt sensitive payment metadata at rest
- **Access Control**: Role-based access to payment data
- **Audit Logging**: Log all payment-related actions with user attribution
- **Data Retention**: Implement appropriate data retention policies

## Business Model and Pricing

### Revenue Model Options

#### 1. Pass-Through Model
- Clients pay Stripe fees directly (2.9% + 30¢)
- WellPlanner CRM charges only subscription fees
- **Pros**: Simple, transparent pricing
- **Cons**: No additional revenue from payments

#### 2. Markup Model
- Add 0.5-1% markup on top of Stripe fees
- Total client cost: 3.4-3.9% + 30¢ per transaction
- **Pros**: Additional revenue stream
- **Cons**: Higher costs for clients

#### 3. Subscription Tier Model
- Include payment processing in higher-tier subscriptions
- Free tier: Limited transactions per month
- Paid tiers: Unlimited transactions at standard rates
- **Pros**: Encourages subscription upgrades
- **Cons**: Complex pricing structure

#### 4. Hybrid Model (Recommended)
- Free tier: 10 transactions per month
- Standard tier: Unlimited transactions at Stripe rates
- Premium tier: Unlimited transactions with 0.2% discount
- **Pros**: Flexible, scalable pricing
- **Cons**: Requires careful tier management

### Competitive Analysis
- **Square**: 2.9% + 30¢ (similar to Stripe)
- **PayPal**: 2.9% + 30¢ + additional fees for some features
- **Authorize.Net**: 2.9% + 30¢ + monthly gateway fee
- **WellPlanner Advantage**: Integrated CRM + payments in one platform

## Monitoring and Analytics

### Key Metrics to Track
- **Transaction Volume**: Total payment volume processed
- **Success Rate**: Percentage of successful payments
- **Average Transaction Size**: Mean payment amount
- **Failed Payment Reasons**: Analysis of payment failures
- **Revenue Attribution**: Payments by business/form/invoice

### Monitoring Tools
- **Stripe Dashboard**: Built-in analytics and monitoring
- **Custom Analytics**: Application-level payment tracking
- **Webhook Monitoring**: Track webhook delivery and processing
- **Error Tracking**: Monitor payment-related errors and exceptions

### Alerting Strategy
- **Failed Payments**: Alert on unusual failure rates
- **High-Value Transactions**: Monitor large payments
- **Webhook Failures**: Alert on webhook processing issues
- **API Rate Limits**: Monitor Stripe API usage

## Testing Strategy

### Development Testing
```bash
# Test payment intent creation
curl -X POST http://localhost:9000/businesses/1/payments/intents \
  -H "Content-Type: application/json" \
  -d '{"amount": 2000, "currency": "USD", "description": "Test payment"}'

# Test webhook handling
curl -X POST http://localhost:9000/webhooks/stripe \
  -H "Content-Type: application/json" \
  -H "Stripe-Signature: test_signature" \
  -d '{"type": "payment_intent.succeeded", "data": {...}}'
```

### Stripe Test Cards
```
# Successful payments
4242424242424242 (Visa)
4000056655665556 (Visa Debit)
5555555555554444 (Mastercard)

# Failed payments
4000000000000002 (Card declined)
4000000000009995 (Insufficient funds)
4000000000000069 (Expired card)
```

### Integration Testing
- Payment flow end-to-end testing
- Webhook event processing validation
- Subscription lifecycle testing
- Refund and dispute handling

## Deployment Considerations

### Production Checklist
- [ ] Stripe account verified and activated
- [ ] Production API keys configured
- [ ] Webhook endpoints registered with Stripe
- [ ] SSL certificates installed and validated
- [ ] Payment database tables created
- [ ] Monitoring and alerting configured
- [ ] Backup and disaster recovery tested

### Performance Optimization
- **Database Indexing**: Optimize payment-related queries
- **Caching**: Cache subscription plans and payment methods
- **Async Processing**: Handle webhooks asynchronously
- **Connection Pooling**: Optimize Stripe API connections

### Scaling Considerations
- **Rate Limiting**: Implement appropriate rate limits
- **Load Balancing**: Distribute payment processing load
- **Database Sharding**: Plan for payment data growth
- **Monitoring**: Scale monitoring with transaction volume

## Support and Maintenance

### Ongoing Maintenance Tasks
- **API Version Updates**: Keep Stripe API version current
- **Security Updates**: Regular security patches and updates
- **Performance Monitoring**: Continuous performance optimization
- **Feature Updates**: Add new Stripe features as they become available

### Customer Support
- **Payment Issues**: Process for handling payment disputes
- **Refund Requests**: Streamlined refund processing
- **Technical Support**: Payment integration troubleshooting
- **Documentation**: Maintain up-to-date payment documentation

## Status and Next Steps

### Current Status
- ✅ **Analysis Complete**: Payment gateway options evaluated
- ✅ **Architecture Designed**: Technical implementation planned
- ✅ **Documentation Created**: Comprehensive implementation guide
- ⏳ **Implementation**: Ready to begin development

### Immediate Next Steps
1. **Stripe Account Setup**: Create and configure Stripe account
2. **Database Schema**: Implement payment-related database tables
3. **Core Services**: Build PaymentService and StripeService
4. **Basic Integration**: Create payment intent and confirmation endpoints

---

*Last Updated: August 17, 2025*  
*Version: 1.0*  
*Status: Ready for Implementation*
