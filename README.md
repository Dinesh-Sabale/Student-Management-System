# Student Management System

A console-based Student Management System developed using Core Java. The project demonstrates object-oriented programming, collections, custom exception handling, file handling, validation, and layered application design.

## Features

- Add student
- View all students
- Search student by ID
- Update student
- Delete student
- Automatic grade calculation
- Input validation
- Custom exceptions
- Persistent storage using a text file
- Clean layered architecture

## Tech Stack

- Java 17+
- Maven
- Core Java
- OOP
- Collections Framework
- Exception Handling
- File Handling

## Project Architecture

```text
Main
  |
StudentService
  |
StudentDAO
  |
students.txt
```

### Package Responsibilities

- `model` - application data model
- `dao` - data access and file persistence
- `service` - business logic and validation
- `exception` - custom application exceptions
- `Main` - console user interface

## How to Run in Eclipse

1. Install JDK 17 or later.
2. Open Eclipse.
3. Select `File -> Import -> Existing Maven Projects`.
4. Select the project folder.
5. Wait for Maven configuration to finish.
6. Open `src/main/java/com/studentmanagement/Main.java`.
7. Right-click `Main.java`.
8. Select `Run As -> Java Application`.

## How to Run from Command Line

```bash
mvn clean compile
mvn exec:java
```

## Sample Operations

The application supports:

```text
1. Add Student
2. View All Students
3. Search Student
4. Update Student
5. Delete Student
6. Exit
```

## Grade System

| Marks | Grade |
|------:|:-----:|
| 90-100 | A+ |
| 80-89 | A |
| 70-79 | B |
| 60-69 | C |
| 50-59 | D |
| 40-49 | E |
| Below 40 | F |

## Concepts Demonstrated

### OOP
- Classes and objects
- Encapsulation
- Separation of responsibilities
- Constructor
- Methods

### Collections
- `ArrayList`
- `Optional`
- Stream filtering

### Exception Handling
- Checked custom exceptions
- `try-catch`
- Input validation

### File Handling
- `Files`
- `Path`
- `BufferedWriter`
- Reading and writing persistent records

## Future Enhancements

- MySQL/JDBC integration
- Login and authentication
- Swing/JavaFX GUI
- Search by name/course
- Sorting and pagination
- Student attendance module

## Resume Description

**Student Management System | Java, OOP, Collections, File Handling**

Developed a console-based Student Management System using Core Java, implementing CRUD operations, OOP principles, collections, custom exception handling, input validation, grade calculation, and file-based data persistence.

## Interview Questions You Should Prepare

1. Why did you use ArrayList?
2. What is encapsulation in this project?
3. Why did you separate DAO and Service layers?
4. What is the purpose of custom exceptions?
5. How is data persisted after closing the application?
6. What happens if duplicate student ID is entered?
7. How does the grade calculation work?
8. Which OOP concepts are used in the project?
9. How would you replace file storage with MySQL?
10. How would you convert this console application into a REST API?
