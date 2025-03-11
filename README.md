# Library Management System

A Spring Boot application for managing books, patrons, and borrowing records in a library.

## Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Setup and Installation](#setup-and-installation)
4. [Running the Application](#running-the-application)
5. [API Documentation](#api-documentation)
6. [Authentication](#authentication)
7. [Testing](#testing)
8. [Contributing](#contributing)
9. [License](#license)

---

## Features
- **Book Management**: Add, update, delete, and retrieve books.
- **Patron Management**: Add, update, delete, and retrieve patrons.
- **Borrowing Records**: Track which books are borrowed by which patrons.
- **Transaction Management**: Ensure data integrity during critical operations.
- **Caching**: Improve performance by caching frequently accessed data.
- **Logging**: Log method calls, exceptions, and performance metrics.
- **Unit Testing**: Validate functionality using JUnit and Mockito.

---

## Technologies Used
- **Spring Boot**: Backend framework.
- **MySQL**: Database for storing books, patrons, and borrowing records.
- **Hibernate**: ORM for database interactions.
- **JUnit**: Unit testing framework.
- **Mockito**: Mocking framework for unit tests.
- **Maven**: Build tool.
- **Swagger (Optional)**: API documentation.

---

## Setup and Installation

### Prerequisites
- Java 17 or higher.
- MySQL Server.
- Maven.

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/rmz882/Library-Management-System.git
   cd Library-Management-System
2. Set Up MySQL Database:

.Create a database named library in MySQL.
.Update the application.properties file with your MySQL credentials: 
   spring.datasource.url=jdbc:mysql://localhost:3306/library
   spring.datasource.username=root
   spring.datasource.password=your_password
   
3.Build the Application:
   mvn spring-boot:run
The application will start on port 8080. You can access it at:
   http://localhost:8080

## API Documentation

Book Endpoints:
  GET /api/books: Retrieve all books.
  GET /api/books/{id}: Retrieve a specific book by ID.
  POST /api/books: Add a new book.
  PUT /api/books/{id}: Update an existing book.
  DELETE /api/books/{id}: Remove a book.

Patron Endpoints
  GET /api/patrons: Retrieve all patrons.
  GET /api/patrons/{id}: Retrieve a specific patron by ID.
  POST /api/patrons: Add a new patron.
  PUT /api/patrons/{id}: Update an existing patron.
  DELETE /api/patrons/{id}: Remove a patron.

## Authentication

The API endpoints are protected using **Basic Authentication**. To access the APIs, you need to provide a username and password.

### Default Credentials
- **Username**: `user`
- **Password**: `password`

### How to Use Basic Authentication
1. **Include Credentials in API Requests**:
   - Use the `Authorization` header with the value `Basic <base64-encoded-credentials>`.
   - To generate the base64-encoded credentials:
     ```bash
     echo -n "user:password" | base64
     ```
     This will output something like:
     ```
     dXNlcjpwYXNzd29yZA==
     ```

2. **Example API Request**:
   - Use a tool like **Postman** or **cURL** to send authenticated requests.
   - Example with **cURL**:
     ```bash
     curl -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" http://localhost:8080/api/books
     ```
   - Example with **Postman**:
     - Set the **Authorization** tab to **Basic Auth**.
     - Enter `user` as the username and `password` as the password.

## Validation and Error Handling

The API enforces input validation and provides meaningful error messages for invalid requests. Below are the details of how validation and error handling are implemented.

---

### Input Validation
The API validates the following for incoming requests:
1. **Required Fields**:
   - Fields marked as `@NotBlank` or `@NotNull` must be provided.
   - Example: `title`, `author`, and `isbn` are required for adding a book.

2. **Data Formats**:
   - The `publicationYear` must be a valid date in the past or present.
   - The `isbn` must match the regex pattern `^(97(8|9))?\d{9}(\d|X)$`.

3. **Custom Validation**:
   - Custom validation logic can be added using `@Valid` annotations and custom validators.

---

### Error Handling
The API handles exceptions gracefully and returns appropriate HTTP status codes and error messages. Below are the common error scenarios:

1. **Validation Errors**:
   - **Status Code**: `400 Bad Request`
   - **Response Body**:
     ```json
     {
       "timestamp": "2023-10-15T12:34:56.789+00:00",
       "status": 400,
       "error": "Bad Request",
       "message": "Validation failed",
       "errors": [
         {
           "field": "title",
           "message": "Title is required"
         },
         {
           "field": "isbn",
           "message": "Invalid ISBN format"
         }
       ]
     }
     ```

2. **Resource Not Found**:
   - **Status Code**: `404 Not Found`
   - **Response Body**:
     ```json
     {
       "timestamp": "2023-10-15T12:34:56.789+00:00",
       "status": 404,
       "error": "Not Found",
       "message": "Book not found with id: 123"
     }
     ```

3. **Internal Server Error**:
   - **Status Code**: `500 Internal Server Error`
   - **Response Body**:
     ```json
     {
       "timestamp": "2023-10-15T12:34:56.789+00:00",
       "status": 500,
       "error": "Internal Server Error",
       "message": "An unexpected error occurred"
     }
     ```

---
## Caching

The application uses **Spring's caching mechanisms** to cache frequently accessed data, such as book details, to improve system performance.

---

### How Caching Works
1. **Cached Data**:
   - Book details are cached to reduce database queries for frequently accessed data.
   - The cache is automatically updated or evicted when data is modified.

2. **Cache Configuration**:
   - The cache is configured using Spring's `@Cacheable`, `@CacheEvict`, and `@CachePut` annotations.
   - The default cache provider is **in-memory caching**.

3. **Cache Names**:
   - `books`: Caches the result of `getAllBooks()`.
   - `book`: Caches the result of `getBookById(Long id)`.

---
## Contributing
Contributions are welcome! If you'd like to contribute, please follow these steps:

1.Fork the repository.
2.Create a new branch for your feature or bugfix.
3.Commit your changes.
4.Submit a pull request.

Contact
If you have any questions or need further assistance, feel free to reach out:
GitHub: rmz882
Email: ramez.shaban.948@gmail.com
