# AIT Login System

A full-stack user authentication application developed using Java, Spring Boot, PostgreSQL, HTML, CSS, and JavaScript.

The application allows users to register, log in securely, maintain authenticated sessions, and access a personalized Welcome to AIT dashboard.

## Features

- User registration with full name, email, and password.
- User login with email and password.
- Password encryption using BCrypt.
- Server-side input validation.
- Duplicate email prevention.
- Session-based authentication.
- Session ID regeneration after login.
- Logout functionality.
- Existing session verification.
- Personalized dashboard after successful login.
- Show/Hide password functionality.
- Responsive frontend interface.

## Technologies Used

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Validation
- Maven

### Database
- PostgreSQL 18

### Frontend
- HTML5
- CSS3
- JavaScript
- Fetch API

### Development Tools
- Visual Studio Code
- Git
- GitHub
- PowerShell

## Project Structure

### Backend

```text
ait-login/
├── src/
│   └── main/
│       ├── java/com/ait/login/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

### Frontend

```text
ait-frontend/
├── index.html
├── style.css
├── script.js
└── README.md
```

## Database Configuration

Database name:

`ait_welcome_db`

Database: PostgreSQL

Configure your database connection in `application.properties`.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ait_welcome_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace the username and password with your local PostgreSQL credentials.

## Running the Backend

1. Start PostgreSQL.
2. Open the backend project in VS Code.
3. Open the terminal.
4. Navigate to the backend folder.
5. Run:

```powershell
.\mvnw.cmd spring-boot:run
```

Backend runs at:

`http://localhost:8080`

## Running the Frontend

1. Open the `ait-frontend` folder in VS Code.
2. Open `index.html`.
3. Right-click and select **Open with Live Server**.

Frontend runs at:

`http://127.0.0.1:5500/index.html`

Make sure the backend is running before using the application.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Authenticate a user |
| GET | `/api/users/session` | Check the current session |
| POST | `/api/users/logout` | Log out the current user |

## Authentication Flow

1. A new user registers with their details.
2. The backend validates the submitted information.
3. The password is securely hashed using BCrypt.
4. User information is stored in PostgreSQL.
5. The user logs in using registered credentials.
6. The backend authenticates the user and creates an authenticated session.
7. The frontend displays the personalized dashboard.
8. The user can log out to terminate the session.

## Learning Outcomes

Through this project, I gained practical experience in:

- Java programming and object-oriented concepts.
- Building REST APIs using Spring Boot.
- Database integration using PostgreSQL.
- User authentication and session management.
- Password hashing and validation.
- Frontend and backend integration.
- Working with Git and GitHub.

## Future Improvements

- Forgot password functionality.
- Email verification.
- User profile management.
- Role-based authentication.
- Deployment to a cloud platform.

## Author

Developed as a full-stack learning project using Java and modern web technologies.