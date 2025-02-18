package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*@PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }*/

    @PostMapping("/insert")
    public List<StudentDTO> createStudentAndReturnAll(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudentAndReturnAll(studentDTO);
    }


    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Fixed: Changed URL to avoid conflict
    @GetMapping("/search")
    public List<StudentDTO> getStudentsByEmailNameAndCourse(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String course) {
        return studentService.findByEmailNameAndCourse(email, name, course);
    }

    // Fixed: Changed URL to avoid conflict
    @GetMapping("/searchByName") // Updated mapping
    public List<StudentDTO> searchStudentsByName(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
