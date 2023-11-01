package com.company.controller;

import com.company.model.Airport;
import com.company.model.Flight;
import com.company.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminPage {
    String ADMIN_PATH = AppConstants.BASE_PATH + "/admin";
    String ADD_AIRPORT = AppConstants.BASE_PATH + "/admin/add-airport";
    String ADD_FLIGHT = AppConstants.BASE_PATH + "/admin/add-flight";
    String AIRPORT_LIST = AppConstants.BASE_PATH + "/admin/airport-list";
    String TICKET_LIST = AppConstants.BASE_PATH + "/admin/flight-list";

    @GetMapping(ADMIN_PATH)
    String adminPage();

    @GetMapping(ADD_AIRPORT)
    String addAirport();

    @PostMapping(ADD_AIRPORT)
    String airport(@Valid @ModelAttribute Airport airport);

    @GetMapping(ADD_FLIGHT)
    String addFlight(Model model);

    @PostMapping(ADD_FLIGHT)
    String flight(@Valid
                  @RequestParam
                  String flightTime,
                  String flightDate,
                  String countOfSeats,
                  String fromAirportId,
                  String toAirportId);

    @GetMapping(AIRPORT_LIST)
    String airportList(Model model);

    @GetMapping(TICKET_LIST)
    String flightList(Model model);
}
