# SmartMetro – Intelligent Metro Management & Smart Card System

SmartMetro is a Java Spring Boot application designed to manage metro stations, routes, passenger accounts, smart cards, recharges, and fare deductions. It uses MySQL to store application data and REST APIs to handle operations.

## Features

- **User Management:** Register users and retrieve user details.
- **Metro Card Management:** Manage smart cards and check card balances.
- **Station Management:** Create and retrieve metro station information.
- **Route Management:** Manage routes between stations.
- **Card Recharge:** Add money to an active metro card.
- **Fare Deduction:** Deduct journey fares from a card balance.
- **Transaction History:** Retrieve recharge and fare transaction records.
- **Input Validation and Error Handling:** Handle invalid requests and application errors.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web / REST APIs
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git and GitHub

## Project Structure

```text
src/main/java/com/smartmetro/smartmetro/
├── controller/    # REST API endpoints
├── entity/        # Database entities
├── exception/     # Exception handling
├── repository/    # Database access
├── service/       # Business logic
└── SmartmetroApplication.java

src/main/resources/
└── application.properties

pom.xml
```

## Prerequisites

- Java 21
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Maven (or the included Maven Wrapper)

## Setup and Run

1. Clone this repository:

   ```bash
   git clone https://github.com/A-shreedevi/SmartMetro.git
   ```

2. Open the project in your Java IDE.

3. Create a MySQL database named `smartmetro`.

4. Configure your database connection in `src/main/resources/application.properties`. Set the database username and provide the password using the `DB_PASSWORD` environment variable. Never commit real passwords or other secrets.

5. Set `DB_PASSWORD` in your IDE's run configuration or your operating environment.

6. Run `SmartmetroApplication.java`.

7. The application will start at:

   `http://localhost:8080`

## User API Documentation

Base URL: `http://localhost:8080`

### 1. Register a User

- **Method:** POST
- **Endpoint:** `/api/users/register`
- **Body:** JSON

Example request:

```http
POST http://localhost:8080/api/users/register
Content-Type: application/json
```

Example JSON body (adjust fields to match your `User` entity and validation rules):

```json

{
   "name": "Test Passenger",
   "email": "testpassenger@example.com",
   "phone": "9876543210",
   "password": "TestPassword123!"
}

```

Registers a new user. The service handles password encoding and assigns the passenger role according to the current implementation.

### 2. Retrieve All Users

- **Method:** GET
- **Endpoint:** `/api/users`

Example:

```http
GET http://localhost:8080/api/users
```

Returns the list of users.

### 3. Retrieve a User by ID

- **Method:** GET
- **Endpoint:** `/api/users/{id}`

Example:

```http
GET http://localhost:8080/api/users/2
```

Returns the user if found; otherwise, returns HTTP 404 Not Found.

### 4. Retrieve a User by Email

- **Method:** GET
- **Endpoint:** `/api/users/email/{email}`

Example:

```http
GET http://localhost:8080/api/users/email/testpassenger@example.com
```

Retrieves a user using their email address.

### 5. Update a User

- **Method:** PUT
- **Endpoint:** `/api/users/{id}`
- **Body:** JSON

Example:

```http
PUT http://localhost:8080/api/users/2
Content-Type: application/json
```

Send a JSON body matching the `User` entity and its validation requirements to update the specified user.

### 6. Delete a User

- **Method:** DELETE
- **Endpoint:** `/api/users/{id}`

Example:

```http
DELETE http://localhost:8080/api/users/2
```

Deletes the specified user and returns a success message if the operation completes.

### 7. User Login

- **Method:** POST
- **Endpoint:** `/api/users/login`
- **Body:** JSON

Example request:

```http
POST http://localhost:8080/api/users/login
Content-Type: application/json
```

Example JSON body:

```json
{
  "email": "testpassenger@example.com",
  "password": "TestPassword123!"
}
```

Returns `Login successful` for valid credentials or HTTP 401 Unauthorized for invalid credentials.

**Security note:** These examples use test credentials only. Do not publish real passwords, and avoid exposing password hashes or sensitive user details in API responses. For a production application, add proper authentication and authorization controls.
## Route API Documentation

Base URL: `http://localhost:8080`

### 1. Add a New Route

- **Method:** POST
- **Endpoint:** `/api/routes`
- **Body:** JSON

Example request:

```http
POST http://localhost:8080/api/routes
Content-Type: application/json
```

Example JSON body (adjust the fields to match your `Route` entity):

```json
{
  "sourceStation": {
    "id": 2
  },
  "destinationStation": {
    "id": 3
  },
  "distance": 15.0,
  "estimatedTime": 30,
  "active": true
}
```

Creates a metro route between two existing stations. The station IDs must refer to valid records in your database.

### 2. Retrieve All Routes

- **Method:** GET
- **Endpoint:** `/api/routes`

Example:

```http
GET http://localhost:8080/api/routes
```

Returns the list of routes.

### 3. Retrieve a Route by ID

- **Method:** GET
- **Endpoint:** `/api/routes/{id}`

Example:

```http
GET http://localhost:8080/api/routes/1
```

Returns the route if found; otherwise, returns HTTP 404 Not Found.

### 4. Update a Route

- **Method:** PUT
- **Endpoint:** `/api/routes/{id}`
- **Body:** JSON

Example request:

```http
PUT http://localhost:8080/api/routes/1
Content-Type: application/json
```

Use a JSON body matching the `Route` entity, including valid source and destination station references where required.

Updates the specified route.

### 5. Delete a Route

- **Method:** DELETE
- **Endpoint:** `/api/routes/{id}`

Example:

```http
DELETE http://localhost:8080/api/routes/1
```

Deletes the specified route and returns a success message if the operation completes.

**Note:** Use test records when updating or deleting routes. Your entity mappings and database relationships determine the exact accepted JSON fields.
## Station API Documentation

Base URL: `http://localhost:8080`

### 1. Add a New Station

- **Method:** POST
- **Endpoint:** `/api/stations`
- **Body:** JSON

Example request:

```http
POST http://localhost:8080/api/stations
Content-Type: application/json
```

Example JSON body (adjust field names to match your `Station` entity):

```json
{
  "name": "Central Station",
  "location": "Chennai",
  "line": "Blue Line",
  "active": true
}
```

Adds a new metro station and returns the saved station details.

### 2. Retrieve All Stations

- **Method:** GET
- **Endpoint:** `/api/stations`

Example:

```http
GET http://localhost:8080/api/stations
```

Returns the list of stations.

### 3. Retrieve a Station by ID

- **Method:** GET
- **Endpoint:** `/api/stations/{id}`

Example:

```http
GET http://localhost:8080/api/stations/2
```

Returns the station if found; otherwise, returns HTTP 404 Not Found.

### 4. Update a Station

- **Method:** PUT
- **Endpoint:** `/api/stations/{id}`
- **Body:** JSON

Example request:

```http
PUT http://localhost:8080/api/stations/2
Content-Type: application/json
```

Example JSON body:

```json
{
  "name": "Updated Central Station",
  "location": "Chennai",
  "line": "Blue Line",
  "active": true
}
```

Updates the station with the specified ID. Adjust the JSON fields to match your `Station` entity.

### 5. Delete a Station

- **Method:** DELETE
- **Endpoint:** `/api/stations/{id}`

Example:

```http
DELETE http://localhost:8080/api/stations/2
```

Deletes the specified station and returns a success message if the operation completes.

**Note:** Use test records when updating or deleting stations. The JSON examples are illustrative; your actual entity fields and database relationships determine which fields are accepted.
## Metro Card API Documentation

Base URL: `http://localhost:8080`

### 1. Create a Metro Card

- **Method:** POST
- **Endpoint:** `/api/cards`
- **Body:** JSON

Example request:

```http
POST http://localhost:8080/api/cards
Content-Type: application/json
```

Example JSON body (adjust the fields to match your `MetroCard` entity and database requirements):

```json
{
  "cardNumber": "SMC10002",
  "balance": 500,
  "status": "ACTIVE"
}
```

Creates a metro card and returns the saved card details.

### 2. Retrieve All Metro Cards

- **Method:** GET
- **Endpoint:** `/api/cards`

Example:

```http
GET http://localhost:8080/api/cards
```

Returns the list of metro cards.

### 3. Retrieve a Card by ID

- **Method:** GET
- **Endpoint:** `/api/cards/{id}`

Example:

```http
GET http://localhost:8080/api/cards/1
```

Returns the card if found; otherwise, returns HTTP 404 Not Found.

### 4. Retrieve a Card by Card Number

- **Method:** GET
- **Endpoint:** `/api/cards/number/{cardNumber}`

Example:

```http
GET http://localhost:8080/api/cards/number/SMC10001
```

Retrieves a card using its card number.

### 5. Update Card Status

- **Method:** PATCH
- **Endpoint:** `/api/cards/{id}/status`
- **Parameter:** `status` (query parameter)

Example:

```http
PATCH http://localhost:8080/api/cards/1/status?status=BLOCKED
```

Updates the card status to the supplied value.

### 6. Block a Metro Card

- **Method:** PATCH
- **Endpoint:** `/api/cards/{id}/block`

Example:

```http
PATCH http://localhost:8080/api/cards/1/block
```

Changes the card status to `BLOCKED`.

### 7. Activate a Metro Card

- **Method:** PATCH
- **Endpoint:** `/api/cards/{id}/activate`

Example:

```http
PATCH http://localhost:8080/api/cards/1/activate
```

Changes the card status to `ACTIVE`.

### 8. Delete a Metro Card

- **Method:** DELETE
- **Endpoint:** `/api/cards/{id}`

Example:

```http
DELETE http://localhost:8080/api/cards/1
```

Deletes the specified card and returns a success message if the operation completes.

**Note:** Use a test card when testing status changes or deletion. The create-card JSON is an example only; your entity relationships may require additional fields.

## Transaction API Documentation

Base URL: `http://localhost:8080`

### 1. Recharge a Metro Card

- **Method:** POST
- **Endpoint:** `/api/transactions/recharge/{cardId}`
- **Parameter:** `amount` (query parameter)

Example request:

```http
POST http://localhost:8080/api/transactions/recharge/1?amount=100
```

Adds money to the specified metro card and returns the transaction details.

### 2. Deduct Journey Fare

- **Method:** POST
- **Endpoint:** `/api/transactions/fare/{cardId}`
- **Parameter:** `fare` (query parameter)

Example request:

```http
POST http://localhost:8080/api/transactions/fare/1?fare=30
```

Deducts the journey fare from the specified metro card and returns the transaction details.

### 3. Retrieve All Transactions

- **Method:** GET
- **Endpoint:** `/api/transactions`

Example request:

```http
GET http://localhost:8080/api/transactions
```

Returns the transaction history available in the system.

### 4. Retrieve Transactions for a Specific Card

- **Method:** GET
- **Endpoint:** `/api/transactions/card/{cardId}`

Example request:

```http
GET http://localhost:8080/api/transactions/card/1
```

Returns transaction records associated with the specified metro card.

### Testing with Postman

1. Start the SmartMetro Spring Boot application.
2. Open Postman.
3. Select the HTTP method specified for the endpoint.
4. Enter the complete request URL.
5. Click **Send** and inspect the JSON response.

**Note:** Use a valid card ID. Recharge and fare deduction requests change the card balance, so use suitable test amounts.

## Learning Outcomes

This project demonstrates practical experience with Java, Spring Boot, REST API development, relational database integration, layered architecture, and API testing.

## Author

**A. Shreedevi**

GitHub: [A-shreedevi](https://github.com/A-shreedevi)