package com.skistation.studentms.repository;

import com.skistation.studentms.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** The interface Student repository. */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {}
