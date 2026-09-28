package com.studentmanagement;

import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.exception.InvalidStudentException;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service =
            new StudentService(new StudentDAO());

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 6 -> System.out.println("Thank you for using the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please select 1-6.");
                }

                if (choice == 6) break;

            } catch (InvalidStudentException | StudentNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("------------- MENU -------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("--------------------------------");
    }

    private static void addStudent() throws InvalidStudentException {
        System.out.println("\n--- Add Student ---");

        int id = readInt("Student ID: ");
        String name = readText("Name: ");
        String email = readText("Email: ");
        String course = readText("Course: ");
        double marks = readDouble("Percentage/Marks (0-100): ");

        Student student = new Student(id, name, email, course, marks);
        service.addStudent(student);

        System.out.println("Student added successfully.");
        System.out.println("Grade: " + student.getGrade());
    }

    private static void viewStudents() {
        List<Student> students = service.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        printHeader();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("\nTotal students: " + students.size());
    }

    private static void searchStudent() throws StudentNotFoundException {
        System.out.println("\n--- Search Student ---");
        int id = readInt("Enter student ID: ");

        Student student = service.getStudent(id);

        printHeader();
        System.out.println(student);
    }

    private static void updateStudent()
            throws InvalidStudentException, StudentNotFoundException {
        System.out.println("\n--- Update Student ---");
        int id = readInt("Enter student ID to update: ");

        Student existing = service.getStudent(id);

        System.out.println("Current student: " + existing);
        System.out.println("Enter new details:");

        String name = readText("Name: ");
        String email = readText("Email: ");
        String course = readText("Course: ");
        double marks = readDouble("Percentage/Marks (0-100): ");

        Student updated = new Student(id, name, email, course, marks);
        service.updateStudent(updated);

        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() throws StudentNotFoundException {
        System.out.println("\n--- Delete Student ---");
        int id = readInt("Enter student ID to delete: ");

        Student student = service.getStudent(id);
        System.out.println("Selected: " + student.getName());

        String confirm = readText("Confirm deletion (yes/no): ");
        if (confirm.equalsIgnoreCase("yes")) {
            service.deleteStudent(id);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void printHeader() {
        System.out.printf("%-5s %-22s %-28s %-18s %10s %5s%n",
                "ID", "NAME", "EMAIL", "COURSE", "MARKS", "GRADE");
        System.out.println("-".repeat(95));
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isBlank()) return value;
            System.out.println("This field cannot be empty.");
        }
    }
}
