
Keep the existing architecture and extend it with the following business features.

---

1. ADD CUSTOMER SERVICE MICROSERVICE

---

Create a new microservice:

customer-service

Register with Eureka.

Create separate MySQL database:

myfin_customer_db

Implement Customer Profile Management.

CustomerProfile Entity:

* id
* userId
* firstName
* lastName
* dateOfBirth
* gender
* email
* mobileNumber
* panNumber
* aadhaarNumber
* occupation
* employerName
* monthlyIncome
* address
* city
* state
* pincode
* creditScore
* kycStatus
* createdAt
* updatedAt

KycStatus Enum:

* PENDING
* VERIFIED
* REJECTED

REST APIs:

POST /api/customers

GET /api/customers/{id}

GET /api/customers/user/{userId}

PUT /api/customers/{id}

PUT /api/customers/{id}/verify

GET /api/customers

Implement DTOs, validation, service layer, repository layer, global exception handling and Swagger documentation.

---

2. ROLE BASED ACCESS CONTROL

---

Extend existing Auth Service.

Roles:

ROLE_CUSTOMER
ROLE_LOAN_OFFICER
ROLE_MANAGER
ROLE_ADMIN

JWT should contain role information.

Restrict APIs appropriately.

Examples:

Customer:

* Manage profile
* Apply for loan
* View EMI schedule

Loan Officer:

* Review applications
* Verify customer KYC

Manager:

* Approve loans
* Reject loans

Admin:

* Manage users

---

3. VEHICLE MANAGEMENT

---

Inside Loan Service add Vehicle Entity.

Vehicle:

* id
* make
* model
* variant
* year
* vehicleType
* exShowroomPrice
* onRoadPrice
* status

VehicleStatus:

* ACTIVE
* INACTIVE

Vehicle APIs:

POST /api/vehicles

PUT /api/vehicles/{id}

DELETE /api/vehicles/{id}

GET /api/vehicles

GET /api/vehicles/{id}

Seed sample data:

Honda City
Hyundai Creta
Tata Nexon
Mahindra XUV700
Maruti Brezza

---

4. LOAN APPLICATION ENHANCEMENT

---

Update existing Loan Application.

LoanApplication:

* id
* customerId
* vehicleId
* vehiclePrice
* downPayment
* loanAmount
* interestRate
* tenureMonths
* status
* remarks
* createdAt
* updatedAt

LoanStatus:

* DRAFT
* SUBMITTED
* UNDER_REVIEW
* APPROVED
* REJECTED
* DISBURSED
* CLOSED

Loan Service must use OpenFeign to fetch customer details from Customer Service.

Store only customerId.

Do not duplicate customer information.

---

5. LOAN ELIGIBILITY ENGINE

---

Create eligibility validation before loan creation.

Rules:

1. Monthly income >= 25000

2. Age between 21 and 60

3. Credit score >= 650

4. Loan amount should not exceed 80% of vehicle price

Create:

LoanEligibilityService

EligibilityResponse:

* eligible
* reason
* maxEligibleAmount

API:

POST /api/loans/check-eligibility

---

6. APPROVAL WORKFLOW ENHANCEMENT

---

Enhance Workflow Service.

Create ApprovalHistory entity.

Fields:

* id
* loanId
* action
* performedBy
* remarks
* timestamp

Actions:

* SUBMITTED
* REVIEWED
* APPROVED
* REJECTED
* DISBURSED

Workflow APIs:

POST /api/workflow/review

POST /api/workflow/approve

POST /api/workflow/reject

GET /api/workflow/history/{loanId}

---

7. EMI SERVICE ENHANCEMENT

---

Generate EMI schedule after loan approval.

EMISchedule:

* id
* loanId
* installmentNumber
* principalAmount
* interestAmount
* emiAmount
* dueDate
* paymentStatus

PaymentStatus:

* PENDING
* PAID
* OVERDUE

API:

GET /api/emi/{loanId}

POST /api/emi/generate/{loanId}

Use reducing balance EMI formula.

---

8. DOCUMENT MANAGEMENT

---

Inside Loan Service add document handling.

LoanDocument:

* id
* loanId
* documentType
* fileName
* filePath
* uploadDate

Document Types:

* PAN
* AADHAAR
* DRIVING_LICENSE
* SALARY_SLIP
* BANK_STATEMENT
* VEHICLE_QUOTATION

Store files locally:

uploads/

Create APIs:

POST /api/documents/upload

GET /api/documents/{id}

GET /api/documents/loan/{loanId}

---

9. CUSTOMER DASHBOARD APIs

---

Create APIs:

GET /api/dashboard/my-profile

GET /api/dashboard/my-loans

GET /api/dashboard/my-emi

GET /api/dashboard/loan/{loanId}

---

10. INTER-SERVICE COMMUNICATION

---

Implement OpenFeign clients:

Loan Service -> Customer Service

Workflow Service -> Loan Service

EMI Service -> Loan Service

Use service discovery via Eureka.

---

11. DATABASE DESIGN

---

Separate databases:

myfin_auth_db

myfin_customer_db

myfin_loan_db

myfin_workflow_db

myfin_emi_db

---

12. CODE QUALITY REQUIREMENTS

---

Use:

* Controller Layer
* Service Layer
* Repository Layer
* DTO Pattern
* Mapper Classes
* Validation
* Global Exception Handling
* OpenFeign
* Spring Security
* JWT Authentication
* Lombok
* Swagger/OpenAPI

Follow clean architecture and enterprise coding standards.

---

13. OUTPUT REQUIREMENT

---

Generate complete source code changes for all services.

Provide:

* Updated folder structure
* Entities
* DTOs
* Controllers
* Services
* Repositories
* Security configuration
* Feign clients
* SQL schema
* API contracts
* application.yml files
* Swagger configuration

The generated code must integrate seamlessly with the existing MYFIN BANK microservices project.
"# myFinBankBackend_1" 
