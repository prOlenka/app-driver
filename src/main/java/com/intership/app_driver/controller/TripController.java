package com.intership.app_driver.controller;

import com.intership.app_driver.entity.Trip;
import com.intership.app_driver.entity.TripEvent;
import com.intership.app_driver.service.TripService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/driver/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/{taskId}")
    public Flux<Trip> getTripsByTaskId(@PathVariable UUID taskId) {
        return tripService.getTripsByTaskId(taskId);
    }

    @PostMapping
    public Mono<Trip> createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @GetMapping("/{tripId}/events")
    public Flux<TripEvent> getTripEvents(@PathVariable UUID tripId) {
        return tripService.getTripEventsByTripId(tripId);
    }
}
