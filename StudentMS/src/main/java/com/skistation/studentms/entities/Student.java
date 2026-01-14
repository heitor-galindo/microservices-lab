package com.skistation.studentms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/** The type Student. */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String school;
  private int age;
  private long inc;

  /**
   * Instantiates a new Student.
   *
   * @param firstName the first name
   * @param school the school
   * @param age the age
   */
  public Student(String firstName, String school, int age) {
    this.firstName = firstName;
    this.school = school;
    this.age = age;
  }
}
