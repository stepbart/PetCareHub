# PetCareHub

PetCareHub is a web application designed for managing the health and care of pets. The application allows users to manage pet profiles, track health records, monitor health status, and schedule veterinary appointments. This project is built using Spring Boot and uses MySQL as the database.

## Features

- **Pet Profile Management:** Add and manage profiles for your pets.
- **Health Records:** Store and view health records for each pet.
- **Health Monitoring:** Track weight, appetite, mood, and activity levels.
- **Appointment Scheduling:** Manage veterinary appointments with reminders.

## Requirements

- **Java 21** (or higher)
- **MySQL** (or a compatible database)
- **Maven** or **Gradle** (for dependency management)
- **Git** (for version control)

## Setup

Follow the steps below to set up and run the application:

### 1. Clone the Repository

First, clone the repository to your local machine:

```bash
git clone git@github.com:stepbart/PetCareHub.git
cd PetCareHub
```
### 2. Database Configuration
Ensure you have MySQL installed and running. Then, create a database:

```sql
CREATE DATABASE petcarehub;
```
### 3. Create a Local Configuration File
In the src/main/resources/ directory, create a file named application-local.properties with the following content:

```properties
spring.datasource.url=URL
spring.datasource.username=USER
spring.datasource.password=PASSWORD
```
Note: Replace URL, USER and PASSWORD with your actual database credentials.

### 4. Configure VM Options
To run the application with the local profile, set the following VM option in your IDE:

In IntelliJ IDEA:
Go to Run/Debug Configurations.
In the VM options field, add:

```plaintext
-Dspring.profiles.active=local
```

### 5. Run the Application
Run the application using IntelliJ IDEA or from the command line:

```bash
./gradlew bootRun
```
Or if you are using Maven:

```bash
./mvnw spring-boot:run
```
### 6. Access the Application
Once the application is running, you can access it at http://localhost:8080.

## Project Structure

This project follows the principles of Hexagonal Architecture (also known as Ports and Adapters Architecture). The goal of this architecture is to decouple the core business logic from the external systems, making the application more modular, testable, and adaptable to change.

### Main Directories and Their Purpose:

- **src/main/java/com/petcarehub/**: Main application source files.
    - **core/**: Contains the core business logic of the application.
        - **domain/**: Domain entities representing the core objects within the system.
        - **ports/**: Interfaces that define the contracts for the application's use cases and data access.
            - **in/**: Inbound ports, which define the operations available to the outside world.
            - **out/**: Outbound ports, which define the contracts for external dependencies, such as databases or external APIs.
        - **usecase/**: Specific use cases implementing business logic.
    - **application/**: Implements the core business logic and use cases defined in the `core` module.
        - **services/**: Service classes implementing the business logic.
        - **dto/**: Data Transfer Objects used to pass data between layers.
        - **mapper/**: Classes responsible for mapping entities to DTOs and vice versa.
    - **infrastructure/**: Handles the interaction with external systems and frameworks.
        - **repository/**: Implementations of the outbound ports, providing access to data storage.
        - **adapter/**: Adapters for integrating with external services and APIs.
        - **config/**: Configuration files for various aspects of the application.
    - **web/**: Handles the presentation layer and communication with clients via REST APIs.
        - **controller/**: REST controllers that handle HTTP requests and responses, delegating business logic to the service layer.

- **src/main/resources/**: Application resources such as configuration files, SQL migration scripts, and static assets.
    - **db/migration/**: Flyway migration scripts that manage database schema changes.

- **docs/uml/**: UML diagrams representing the application architecture.
    - **architecture-overview.puml**: Diagram showing the overall structure of the application following the Hexagonal Architecture.
    - **use-case.puml**: Diagram depicting the main use cases of the application and their interaction with different layers.

### Architectural Overview

This project is designed using Hexagonal Architecture to ensure a clear separation of concerns:

- **Core**: The core of the application contains the business logic and is independent of external frameworks or technologies. This makes it easier to test and maintain.
- **Application**: This layer implements the business logic defined in the core and handles the orchestration of use cases.
- **Infrastructure**: This layer deals with the implementation details, such as data storage and external API calls. It interacts with the core through well-defined interfaces (ports).
- **Web**: The web layer is responsible for exposing the application's functionality through RESTful APIs, making it accessible to clients.


## License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
