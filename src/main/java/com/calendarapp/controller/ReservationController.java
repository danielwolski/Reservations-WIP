package com.calendarapp.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendarapp.rest.DailyReservations;
import com.calendarapp.rest.ReservationRequest;
import com.calendarapp.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<DailyReservations> getSlots(@PathVariable("date") String date) {
        LocalDate reservationDate = LocalDate.parse(date);
        DailyReservations availability = reservationService.getSlots(reservationDate);
        return ResponseEntity.ok(availability);
    }

    @GetMapping("/{date}")
    public ResponseEntity<DailyReservations> getReservationsByUser(@PathVariable("date") String date) {
        LocalDate reservationDate = LocalDate.parse(date);
        DailyReservations availability = reservationService.getSlots(reservationDate);
        return ResponseEntity.ok(availability);
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            reservationService.createReservation(request);
            return ResponseEntity.ok().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
