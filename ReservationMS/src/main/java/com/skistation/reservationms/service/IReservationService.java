package com.skistation.reservationms.service;

import com.skistation.reservationms.dto.StudentDTO;
import com.skistation.reservationms.entities.Reservation;

public interface IReservationService {
    public Reservation addReservation(StudentDTO student);
}
