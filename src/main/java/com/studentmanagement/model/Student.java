package com.studentmanagement.model;

import java.util.Locale;

public class Student {
    private int id;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int id, String name, String email, String course, double marks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks) { this.marks = marks; }

    public double getPercentage() {
        return marks;
    }

    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        if (marks >= 40) return "E";
        return "F";
    }

    public String toFileString() {
        return id + "|" + clean(name) + "|" + clean(email) + "|" +
               clean(course) + "|" + String.format(Locale.US, "%.2f", marks);
    }

    private String clean(String value) {
        return value.replace("|", " ");
    }

    public static Student fromFileString(String line) {
        String[] p = line.split("|", -1);
        if (p.length != 5) return null;

        try {
            return new Student(
                Integer.parseInt(p[0]),
                p[1],
                p[2],
                p[3],
                Double.parseDouble(p[4])
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "%-5d %-22s %-28s %-18s %7.2f%% %5s",
            id, name, email, course, marks, getGrade()
        );
    }
}
