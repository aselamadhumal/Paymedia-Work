package com.example.demo.service.Student;

import com.example.demo.dto.StudentDTO;
import java.util.List;

public interface StudentService {

    List<StudentDTO> createStudentAndReturnAll(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);

    List<StudentDTO> getStudentsByName(String name);

    List<StudentDTO> findByEmailNameAndCourse(String email, String name, String course);
}
