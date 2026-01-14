package com.skistation.studentms.controller;

import com.skistation.studentms.entities.Student;
import com.skistation.studentms.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** The type Student controller. */
@RestController
@RequestMapping(path = "/students")
public class StudentController {

  @Autowired private StudentRepository studentRepository;

  /**
   * Create student response entity.
   *
   * @param student the student
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    Student saved = studentRepository.save(student);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  /**
   * Gets all students.
   *
   * @return the all students
   */
  @GetMapping("/all")
  @PreAuthorize("hasRole('USER')")
  public List<Student> getAllStudents() {
    List<Student> list = new ArrayList<>();
    studentRepository.findAll().forEach(list::add);
    return list;
  }

  /**
   * Gets student by id.
   *
   * @param id the id
   * @return the student by id
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('STUDENT.READ')")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    Optional<Student> s = studentRepository.findById(id);
    return s.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Update student response entity.
   *
   * @param id the id
   * @param student the student
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(
      @PathVariable Long id, @RequestBody Student student) {
    Optional<Student> existing = studentRepository.findById(id);
    if (existing.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Student toUpdate = existing.get();
    toUpdate.setFirstName(student.getFirstName());
    toUpdate.setSchool(student.getSchool());
    toUpdate.setAge(student.getAge());
    toUpdate.setInc(student.getInc());
    Student saved = studentRepository.save(toUpdate);
    return ResponseEntity.ok(saved);
  }

  /**
   * Delete student response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    Optional<Student> existing = studentRepository.findById(id);
    if (existing.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    studentRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
