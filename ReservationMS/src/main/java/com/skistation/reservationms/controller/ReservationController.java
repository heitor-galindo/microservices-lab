package com.skistation.reservationms.controller;

import com.skistation.reservationms.clients.StudentClient;
import com.skistation.reservationms.dto.StudentDTO;
import com.skistation.reservationms.entities.Reservation;
import com.skistation.reservationms.service.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

  @Autowired private StudentClient studentClient;

  @Autowired private IReservationService reservationService;

  @PostMapping
  public Reservation addReservation(@RequestParam Long studentId) {
    StudentDTO student = studentClient.getStudentById(studentId);
    if (student == null) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Student not found with ID " + studentId);
    }
    // Call your service logic
    log.info(
        "Reservation added for student {} with NIC {}",
        student.getNomStudent(),
        student.getNicStudent());
    return reservationService.addReservation(student);
  }
}
