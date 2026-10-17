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