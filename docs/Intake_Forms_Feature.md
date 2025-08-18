# Intake Forms Feature Documentation

## Overview
The Intake Forms feature enables clients to create custom forms and send them to their users for data collection. This feature provides a complete form builder, public form sharing, and response management system integrated into the WellPlanner CRM.

## Feature Capabilities

### Core Functionality
- **Dynamic Form Creation**: Clients can build custom forms with various field types
- **Public Form Sharing**: Forms are accessible via unique URLs without authentication
- **Response Collection**: All form submissions are stored and accessible to form owners
- **Status Management**: Forms can be in DRAFT, ACTIVE, or INACTIVE states
- **Business Isolation**: Forms are scoped to specific businesses for security

### Supported Field Types
- **Text Input**: Single-line text fields
- **Email**: Email validation with proper formatting
- **Phone**: Phone number input with validation
- **Dropdown**: Select from predefined options
- **Checkbox**: Multiple selection options
- **Radio**: Single selection from options
- **Date**: Date picker input
- **File Upload**: Document/image upload capability
- **Textarea**: Multi-line text input

## Database Schema

### Tables Created (Evolution 18.sql)

#### `intake_forms`
| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT | Primary key, auto-increment |
| `business_id` | BIGINT | Foreign key to businesses table |
| `title` | VARCHAR(255) | Form title/name |
| `description` | TEXT | Optional form description |
| `form_schema` | JSON | Form structure and field definitions |
| `public_id` | VARCHAR(36) | UUID for public form access |
| `status` | ENUM | DRAFT, ACTIVE, or INACTIVE |
| `created_at` | TIMESTAMP | Form creation timestamp |
| `updated_at` | TIMESTAMP | Last modification timestamp |

#### `form_responses`
| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT | Primary key, auto-increment |
| `form_id` | BIGINT | Foreign key to intake_forms |
| `response_data` | JSON | Submitted form data |
| `submitter_email` | VARCHAR(255) | Optional submitter email |
| `submitter_name` | VARCHAR(255) | Optional submitter name |
| `submitted_at` | TIMESTAMP | Submission timestamp |
| `ip_address` | VARCHAR(45) | Submitter IP address |
| `user_agent` | TEXT | Browser user agent string |

## API Endpoints

### Form Management (Business-scoped)
```
GET     /businesses/:businessId/forms
        # List all forms for a business
        Response: { "status": "success", "forms": [...] }

GET     /businesses/:businessId/forms/:formId
        # Get specific form details
        Response: { "status": "success", "form": {...} }

POST    /businesses/:businessId/forms
        # Create new form
        Body: {
          "title": "Contact Form",
          "description": "Customer contact information",
          "formSchema": {
            "fields": [...],
            "submitButtonText": "Submit",
            "successMessage": "Thank you!"
          }
        }

PUT     /businesses/:businessId/forms/:formId
        # Update existing form
        Body: {
          "title": "Updated Title",
          "formSchema": {...},
          "status": "ACTIVE"
        }

PATCH   /businesses/:businessId/forms/:formId/status
        # Update form status only
        Body: { "status": "ACTIVE" }

DELETE  /businesses/:businessId/forms/:formId
        # Delete form and all responses

GET     /businesses/:businessId/forms/:formId/responses
        # Get all responses for a form
        Response: { "status": "success", "responses": [...] }
```

### Public Form Access (No authentication required)
```
GET     /forms/:publicId
        # Render public form for filling
        Response: {
          "status": "success",
          "form": {
            "id": "uuid",
            "title": "Form Title",
            "description": "Form description",
            "schema": {...}
          }
        }

POST    /forms/:publicId/submit
        # Submit form response
        Body: {
          "responseData": {
            "field1": "value1",
            "field2": "value2"
          },
          "submitterEmail": "user@example.com",
          "submitterName": "John Doe"
        }
```

## Form Schema Structure

### Basic Form Schema
```json
{
  "fields": [
    {
      "id": "unique_field_id",
      "fieldType": "text|email|phone|dropdown|checkbox|radio|date|file|textarea",
      "label": "Field Label",
      "required": true|false,
      "placeholder": "Optional placeholder text",
      "options": ["Option 1", "Option 2"], // For dropdown/radio/checkbox
      "validation": {
        "minLength": 5,
        "maxLength": 100,
        "pattern": "regex_pattern",
        "min": 0,
        "max": 999
      }
    }
  ],
  "submitButtonText": "Submit Form",
  "successMessage": "Thank you for your submission!",
  "redirectUrl": "https://example.com/thank-you"
}
```

### Field Type Examples

#### Text Field
```json
{
  "id": "full_name",
  "fieldType": "text",
  "label": "Full Name",
  "required": true,
  "placeholder": "Enter your full name",
  "validation": {
    "minLength": 2,
    "maxLength": 50
  }
}
```

#### Email Field
```json
{
  "id": "email",
  "fieldType": "email",
  "label": "Email Address",
  "required": true,
  "placeholder": "your@email.com"
}
```

#### Dropdown Field
```json
{
  "id": "service_type",
  "fieldType": "dropdown",
  "label": "Service Type",
  "required": true,
  "options": [
    "Consulting",
    "Development",
    "Support",
    "Other"
  ]
}
```

#### File Upload Field
```json
{
  "id": "resume",
  "fieldType": "file",
  "label": "Upload Resume",
  "required": false,
  "validation": {
    "maxSize": 5242880,
    "allowedTypes": ["pdf", "doc", "docx"]
  }
}
```

## Usage Examples

### Creating a Contact Form
```bash
POST /businesses/123/forms
Content-Type: application/json

{
  "title": "Contact Us",
  "description": "Get in touch with our team",
  "formSchema": {
    "fields": [
      {
        "id": "name",
        "fieldType": "text",
        "label": "Your Name",
        "required": true
      },
      {
        "id": "email",
        "fieldType": "email",
        "label": "Email Address",
        "required": true
      },
      {
        "id": "message",
        "fieldType": "textarea",
        "label": "Message",
        "required": true,
        "placeholder": "How can we help you?"
      }
    ],
    "submitButtonText": "Send Message",
    "successMessage": "Thank you! We'll get back to you soon."
  }
}
```

### Submitting a Form Response
```bash
POST /forms/550e8400-e29b-41d4-a716-446655440000/submit
Content-Type: application/json

{
  "responseData": {
    "name": "John Smith",
    "email": "john@example.com",
    "message": "I'm interested in your services."
  },
  "submitterEmail": "john@example.com",
  "submitterName": "John Smith"
}
```

## Form Status Workflow

### Status Types
- **DRAFT**: Form is being created/edited, not accessible publicly
- **ACTIVE**: Form is live and accepting submissions
- **INACTIVE**: Form is disabled, no longer accepting submissions

### Status Transitions
```
DRAFT → ACTIVE    (Publish form)
ACTIVE → INACTIVE (Disable form)
INACTIVE → ACTIVE (Re-enable form)
ACTIVE → DRAFT    (Edit form)
```

## Security Features

### Access Control
- **Business Isolation**: Forms are scoped to specific businesses
- **Public ID Security**: UUID-based public IDs prevent enumeration attacks
- **Status Validation**: Only ACTIVE forms accept submissions
- **Input Validation**: All form data is validated against schema

### Data Protection
- **IP Tracking**: Submitter IP addresses are logged
- **User Agent Logging**: Browser information captured
- **Timestamp Tracking**: All actions are timestamked
- **JSON Sanitization**: Response data is properly escaped

## Integration Points

### With Existing CRM Features
- **Business Association**: Forms belong to specific businesses
- **User Management**: Form access controlled by business ownership
- **Database Integration**: Uses existing database connection and transactions

### Future Enhancements
- **Email Notifications**: Send alerts on form submissions
- **Form Analytics**: Track submission rates and completion times
- **Form Templates**: Pre-built form templates for common use cases
- **Conditional Logic**: Show/hide fields based on responses
- **Multi-page Forms**: Support for complex, multi-step forms

## Technical Architecture

### Backend Components
- **`IntakeForm.scala`**: Data models and JSON formatters
- **`IntakeFormsAPI.scala`**: Database access layer with CRUD operations
- **`IntakeFormsController.scala`**: REST API endpoints and business logic
- **`Module.scala`**: Dependency injection configuration
- **`18.sql`**: Database evolution for tables and indexes

### Design Patterns
- **Repository Pattern**: Database operations abstracted in API layer
- **DTO Pattern**: Separate request/response models from domain models
- **Try/Success/Failure**: Functional error handling throughout
- **JSON Schema**: Flexible form definitions using JSON structure

## Testing Strategy

### API Testing
```bash
# Test form creation
curl -X POST http://localhost:9000/businesses/1/forms \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Form","formSchema":{"fields":[]}}'

# Test public form access
curl http://localhost:9000/forms/PUBLIC_ID

# Test form submission
curl -X POST http://localhost:9000/forms/PUBLIC_ID/submit \
  -H "Content-Type: application/json" \
  -d '{"responseData":{"field1":"value1"}}'
```

### Database Testing
- Verify form creation and retrieval
- Test response storage and querying
- Validate foreign key constraints
- Check index performance

## Deployment Considerations

### Database Migration
- Evolution 18.sql will run automatically on server start
- Backup database before deploying to production
- Monitor migration performance on large datasets

### Performance Optimization
- Form schema JSON is indexed for fast retrieval
- Response queries are optimized with proper indexes
- Consider caching for frequently accessed public forms

### Monitoring
- Track form submission rates
- Monitor API response times
- Alert on failed form submissions
- Log security-related events

## Status
- ✅ **Database Schema**: Complete with proper indexing
- ✅ **Backend API**: Full CRUD operations implemented
- ✅ **Data Models**: Comprehensive with validation
- ✅ **Controller Layer**: REST endpoints with error handling
- ✅ **Dependency Injection**: Properly configured
- ✅ **Compilation**: All code compiles successfully
- ⏳ **Frontend UI**: Form builder and renderer (future phase)
- ⏳ **Testing**: End-to-end API testing (next step)

---

*Last Updated: August 17, 2025*  
*Version: 1.0*  
*Feature Status: Backend Foundation Complete*
