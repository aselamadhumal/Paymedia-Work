package com.example.demo.service.Student;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findByEmailNameAndCourse(String email, String name, String course) {
        List<Student> students = studentRepository.findByEmailAndNameAndCourse(email, name, course);
        return students.stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return students.stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> createStudentAndReturnAll(StudentDTO studentDTO) {
        studentRepository.insertStudent(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getCourse());

         // Use save instead of native insert
        return studentRepository.findAll().stream()
                .map(stu -> new StudentDTO(stu.getId(), stu.getName(), stu.getEmail(), stu.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getCourse());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setCourse(studentDTO.getCourse());
        studentRepository.save(student);
        return studentDTO;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(Math.toIntExact(id)); // Corrected: No need for Math.toIntExact
    }
}
