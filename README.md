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

## API Testing

Use Postman to test the REST API endpoints for user management, cards, stations, routes, recharges, fare deductions, and transaction history.

## Learning Outcomes

This project demonstrates practical experience with Java, Spring Boot, REST API development, relational database integration, layered architecture, and API testing.

## Author

**A. Shreedevi**

GitHub: [A-shreedevi](https://github.com/A-shreedevi)