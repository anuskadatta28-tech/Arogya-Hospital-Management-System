# 🏥 Arogya Hospital – Hospital Management System

Arogya Hospital is a **Hospital Appointment and Patient Record Management System** developed using Java, Spring Boot, MySQL, JDBC and Thymeleaf.

The application provides a centralized platform to manage patient records, doctor information and hospital appointments through a web-based interface.

## ✨ Features

- **Patient Management**
  - Register new patients.
  - View patient records.
  - Update patient information.
  - Delete patient records.

- **Doctor Management**
  - Add new doctors.
  - View doctor details.
  - Update doctor information.
  - Delete doctor records.

- **Appointment Management**
  - Book appointments by selecting patients and doctors.
  - View appointment records.
  - Update appointments.
  - Cancel appointments.
  - Validate doctor availability and prevent duplicate bookings for the same time slot.

- **Database Management**
  - MySQL database integration.
  - JDBC-based database operations.
  - Relational tables with primary and foreign key constraints.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Core programming language |
| Spring Boot | Backend application framework |
| Spring JDBC | Database connectivity |
| MySQL | Relational database |
| Thymeleaf | Dynamic HTML rendering |
| HTML5 | Webpage structure |
| CSS3 | Website styling |
| Maven | Project and dependency management |
| VS Code | Development environment |

## 📂 Project Structure

```text
Arogya-Hospital-Management-System/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/arogya/hospital/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       ├── service/
│       │       └── ArogyaHospitalApplication.java
│       │
│       └── resources/
│           ├── static/
│           │   └── css/
│           │       └── style.css
│           ├── templates/
│           └── application.properties
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## 🗄️ Database Design

The project uses MySQL with three main tables:

1. **patients** – Stores patient information such as name, age, gender, contact details and address.
2. **doctors** – Stores doctor information, specialization and availability.
3. **appointments** – Stores appointment details, including patient ID, doctor ID, date, time and appointment status.

The appointment table maintains relationships with the patient and doctor tables using foreign keys.

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/anuskadatta28-tech/Arogya-Hospital-Management-System.git
```

### 2. Open the Project

```bash
cd Arogya-Hospital-Management-System
```

### 3. Create the MySQL Database

Open MySQL Workbench and create the database:

```sql
CREATE DATABASE arogya_hospital;
```

Create the required tables using the database schema provided with the project.

### 4. Configure Database Credentials

Open:

`src/main/resources/application.properties`

Configure your MySQL connection:

```properties
spring.application.name=ArogyaHospital
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/arogya_hospital
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.sql.init.mode=never
```

Set your MySQL password as the `DB_PASSWORD` environment variable before running the application.

For Windows PowerShell:

```powershell
$env:DB_PASSWORD="your_mysql_password"
```

### 5. Run the Application

Using the Maven wrapper on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### 6. Open in Browser

Visit:

```text
http://localhost:8080
```

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Object-Oriented Programming using Java.
- Building web applications using Spring Boot.
- Database connectivity and CRUD operations using JDBC.
- Designing relational databases using MySQL.
- Implementing the Model-View-Controller (MVC) architecture.
- Integrating frontend templates with backend functionality.

## 👩‍💻 Developer  

**Anuska Datta** and 
**Bhavya Sharma**

B.Tech – Computer Science and Engineering  
Chandigarh University

GitHub: [@anuskadatta28-tech](https://github.com/anuskadatta28-tech)

---

⭐ If you find this project interesting, feel free to explore the repository!
