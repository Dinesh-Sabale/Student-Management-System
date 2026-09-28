package com.studentmanagement.service;

import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.exception.InvalidStudentException;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) throws InvalidStudentException {
        validate(student);

        if (studentDAO.findById(student.getId()).isPresent()) {
            throw new InvalidStudentException("Student ID already exists.");
        }

        studentDAO.save(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public Student getStudent(int id) throws StudentNotFoundException {
        Optional<Student> student = studentDAO.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }
        return student.get();
    }

    public void updateStudent(Student student)
            throws InvalidStudentException, StudentNotFoundException {
        validate(student);
        studentDAO.update(student);
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        studentDAO.delete(id);
    }

    private void validate(Student student) throws InvalidStudentException {
        if (student.getId() <= 0)
            throw new InvalidStudentException("ID must be greater than 0.");

        if (student.getName() == null || student.getName().isBlank())
            throw new InvalidStudentException("Name cannot be empty.");

        if (!student.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new InvalidStudentException("Please enter a valid email.");

        if (student.getCourse() == null || student.getCourse().isBlank())
            throw new InvalidStudentException("Course cannot be empty.");

        if (student.getMarks() < 0 || student.getMarks() > 100)
            throw new InvalidStudentException("Marks must be between 0 and 100.");
    }
}
