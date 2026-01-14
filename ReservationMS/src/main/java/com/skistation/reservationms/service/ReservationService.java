package com.skistation.reservationms.service;

import com.skistation.reservationms.dto.StudentDTO;
import com.skistation.reservationms.entities.Reservation;
import com.skistation.reservationms.repository.ReservationRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Override
  public Reservation addReservation(StudentDTO student) {
    Reservation res = new Reservation();
    if (student == null) {
      throw new IllegalArgumentException("Student does not exist.");
    }

    res.setStudentId(student.getIdStudent());
    res.setStartDate(LocalDate.now());
    res.setEndDate(LocalDate.now().plusYears(1));
    res.setEstValide(true);

    return reservationRepository.save(res);
  }
}
