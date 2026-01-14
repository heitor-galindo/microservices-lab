package com.skistation.reservationms.controller;

import com.skistation.reservationms.clients.StudentClient;
import com.skistation.reservationms.dto.StudentDTO;
import com.skistation.reservationms.entities.Reservation;
import com.skistation.reservationms.repository.ReservationRepository;
import com.skistation.reservationms.service.IReservationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

//  @Autowired private ReservationRepository reservationRepository;

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
//
//  // Create
//  @PostMapping
//  public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
//    Reservation saved = reservationRepository.save(reservation);
//    return new ResponseEntity<>(saved, HttpStatus.CREATED);
//  }
//
//  // Read all
//  @GetMapping
//  public List<Reservation> getAllReservations() {
//    List<Reservation> list = new ArrayList<>();
//    reservationRepository.findAll().forEach(list::add);
//    return list;
//  }
//
//  // Read by id
//  @GetMapping("/{id}")
//  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
//    Optional<Reservation> r = reservationRepository.findById(id);
//    return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//  }
//
//  // Get student info for a reservation
//  @GetMapping("/{id}/student")
//  public ResponseEntity<StudentDTO> getStudentForReservation(@PathVariable Long id) {
//    Optional<Reservation> r = reservationRepository.findById(id);
//    if (r.isEmpty()) {
//      return ResponseEntity.notFound().build();
//    }
//    Reservation reservation = r.get();
//    Long studentId = reservation.getStudentId();
//    if (studentId == null) {
//      return ResponseEntity.badRequest().build();
//    }
//    StudentDTO student = null;
//    try {
//      student = studentClient.getStudentById(studentId);
//    } catch (Exception e) {
//      // If the downstream call fails, return 502 Bad Gateway
//      return ResponseEntity.status(502).build();
//    }
//    if (student == null) {
//      return ResponseEntity.notFound().build();
//    }
//    return ResponseEntity.ok(student);
//  }

  // Update
  //    @PutMapping("/{id}")
  //    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody
  // Reservation reservation) {
  //        Optional<Reservation> existing = reservationRepository.findById(id);
  //        if (existing.isEmpty()) {
  //            return ResponseEntity.notFound().build();
  //        }
  //        Reservation toUpdate = existing.get();
  //        toUpdate.setYearUniv(reservation.getYearUniv());
  //        toUpdate.setValid(reservation.isValid());
  //        Reservation saved = reservationRepository.save(toUpdate);
  //        return ResponseEntity.ok(saved);
  //    }
//
//  // Delete
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
//    Optional<Reservation> existing = reservationRepository.findById(id);
//    if (existing.isEmpty()) {
//      return ResponseEntity.notFound().build();
//    }
//    reservationRepository.deleteById(id);
//    return ResponseEntity.noContent().build();
//  }
}
