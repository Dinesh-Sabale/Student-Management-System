package com.studentmanagement.dao;

import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StudentDAO {
    private static final Path DATA_FILE = Paths.get("data", "students.txt");
    private final List<Student> students = new ArrayList<>();

    public StudentDAO() {
        loadFromFile();
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public void save(Student student) {
        students.add(student);
        persist();
    }

    public void update(Student updatedStudent) throws StudentNotFoundException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                persist();
                return;
            }
        }
        throw new StudentNotFoundException("Student with ID " + updatedStudent.getId() + " not found.");
    }

    public void delete(int id) throws StudentNotFoundException {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (!removed) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }
        persist();
    }

    private void loadFromFile() {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
                return;
            }

            for (String line : Files.readAllLines(DATA_FILE)) {
                if (!line.isBlank()) {
                    Student student = Student.fromFileString(line);
                    if (student != null) students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not load saved data.");
        }
    }

    private void persist() {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE)) {
                for (Student student : students) {
                    writer.write(student.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not save student data.");
        }
    }
}
