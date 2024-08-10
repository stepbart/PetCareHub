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
- src/main/java/com/petcarehub/: Main application source files\
- src/main/resources/: Application resources
docs/uml/: UML diagrams representing the application architecture.
Contributing
If you'd like to contribute, please fork the repository, create a new branch, and submit a pull request.