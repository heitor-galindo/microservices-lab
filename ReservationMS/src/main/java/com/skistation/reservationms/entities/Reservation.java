package com.skistation.reservationms.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long idReservation;

  @Temporal(TemporalType.DATE)
  LocalDate startDate;

  @Temporal(TemporalType.DATE)
  LocalDate endDate;

  boolean estValide;

  Long roomId; // reference to remote Room
  Long studentId; // reference to remote Student
}
