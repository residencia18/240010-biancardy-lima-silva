# API README

## Overview
This API serves as a backend system for managing appointments, doctors, and patients within a healthcare environment. It provides endpoints for scheduling appointments, registering doctors and patients, updating their information, and listing details.

## Technologies Used
- Java
- Spring Boot
- MySQL
- Spring Security
- Swagger for API documentation

## Security
Spring Security has been implemented for token-based authentication, ensuring that only authorized users can access the endpoints. The API requires a bearer token for authentication.

Sensitive information such as database credentials and tokens are managed using environment variables to enhance security.

## Endpoints

### Appointment Controller (`/appointments`)
- `POST /appointments`: Schedule a new appointment.
- `DELETE /appointments`: Cancel an appointment.

### Doctor Controller (`/doctors`)
- `POST /doctors`: Register a new doctor.
- `GET /doctors`: Get a paginated list of doctors.
- `GET /doctors/{id}`: Get details of a specific doctor.
- `PUT /doctors`: Update doctor information.
- `DELETE /doctors/{id}`: Disable a doctor.

### Patient Controller (`/patients`)
- `POST /patients`: Register a new patient.
- `GET /patients`: Get a paginated list of patients.
- `GET /patients/{id}`: Get details of a specific patient.
- `PUT /patients`: Update patient information.
- `DELETE /patients/{id}`: Disable a patient.

### Authentication

#### Login Endpoint
Endpoint for user authentication.

- **URL:** `/login`
- **Method:** `POST`
- **Request Body:** 
  ```json
  {
    "login": "example",
    "password": "password"
  }
  ```

- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlIiwiaWF0IjoxNTE2MjM5MDIyfQ.M41KNH0kkFj_QTcHcTCWJXGoYSn83bDjTfVr8d2wFSRpeWp0zAdmwIC35usgGwGyvOb4u64BFzV9u6GWqj34gQ"
  }
  ```


#### Registration
Endpoint to register a new user.
- **URL:** `/register`
- **Method:** `POST`
- **Request Body:** 
  ```json
  {
    "username": "example",
    "email": "example@example.com",
    "password": "password"
  }
  ```

- **Response:**
  ```json
  {
    "userId": 1,
    "username": "example",
    "email": "example@example.com"
  }
  ```

#### Password Recovery
Endpoints for password recovery.

- **URL:** ` /reset-password/request`
- **Method:** `POST`
- **Request Body:** 
  ```json
  {
     "email": "example@example.com"
  }
  ```

- **Response:**
  ```
  {
     A password reset email has been sent to example@example.com
  }
  ```

- **URL:** `/reset-password/reset`
- **Method:** `POST`
- **Request Body:** 
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlIiwiaWF0IjoxNTE2MjM5MDIyfQ.M41KNH0kkFj_QTcHcTCWJXGoYSn83bDjTfVr8d2wFSRpeWp0zAdmwIC35usgGwGyvOb4u64BFzV9u6GWqj34gQ",
    "password": "newpassword"
  }
- **Response:**
  ```
  {
      Password updated successfully
  }
  ```

## Logging
SLF4J is utilized for logging, offering insights into the application's execution flow. The logs capture key actions such as appointment scheduling, doctor and patient registration, and updates to their information.

## Data Validation
Validation of input data is conducted through Jakarta Bean Validation (@Valid). This validation mechanism ensures that only valid data is accepted by the API, thereby enhancing the integrity and security of the data.

## Deployment
Prior to deployment, it's imperative to configure environment variables containing sensitive information appropriately. The application is intended to be deployed with PostgreSQL as the backend database.

## Documentation
Swagger is leveraged to generate comprehensive API documentation. Upon running the application, accessing the API endpoints through a web browser provides detailed information about each endpoint, including their request and response schemas.








