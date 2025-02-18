package com.example.demo.repository;


import com.example.demo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Student findByEmail(@Param("email") String email);

    /* @Query(value = "SELECT * FROM students WHERE course = :course", nativeQuery = true)
    List<Student> findByCourse(@Param("course") String course);*/

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);

    //native
    @Query(value = "SELECT * FROM students WHERE id = :id", nativeQuery = true)
    Optional<Student> findByIdNative(@Param("id") Long id);


    List<Student> findByEmailAndNameAndCourse(String email, String name,String course);

    //List<Student>findAllByCourse(String course);


    //insert query
    @Query(value = "INSERT INTO students(name, email, course) VALUES(:name, :email, :course)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertStudent(@Param("name") String name,
                       @Param("email") String email,
                       @Param("course") String course);


    // @Param - bind method parameters to the named parameters in your JPQL or SQL queries.

}

