package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NamedEntityGraph
@Entity
@Table(name = "students")
// Ensuring the table name is "students"
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID generation
    private Long id;

    @Column(name = "name", nullable = false)  // Optional: Adding constraints like non-null
    private String name;

    @Column(name = "email", nullable = false, unique = true)  // Email should be unique and non-null
    private String email;

    @Column(name = "course", nullable = false)  // Ensuring course is not null
    private String course;

    // Default constructor
    public Student() {
    }



}
