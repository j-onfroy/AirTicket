package com.company.controller;

import com.company.model.Airport;
import com.company.model.Ticket;
import com.company.payload.TicketDTO;
import com.company.service.AirportService;
import com.company.service.FlightService;
import com.company.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPageImpl implements AdminPage {
    private final AirportService airportService;
    private final TicketService ticketService;
    private final FlightService flightService;

    @Override
    public String adminPage() {
        return "admin/add";
    }

    @Override
    public String addAirport() {
        return "admin/add-airport";
    }

    @Override
    public String airport(@Valid @ModelAttribute Airport airport) {
        airportService.saveAirport(airport);
        return "admin/add";
    }

    @Override
    public String addFlight(Model model) {
        List<String> airports = airportService.allAirportName();
        model.addAttribute("airports", airports);
        return "admin/add-flight";
    }

    @Override
    public String flight(
            @Valid @RequestParam String flightTime,
            @Valid @RequestParam String flightDate,
            @Valid @RequestParam String countOfSeats,
            @Valid @RequestParam String fromAirportId,
            @Valid @RequestParam String toAirportId) {
        flightService.save(flightTime,flightDate,countOfSeats,fromAirportId,toAirportId);
        return "admin/add";
    }

    @Override
    public String airportList(Model model) {
        List<Airport> airports = airportService.allAirport();
        model.addAttribute("airports", airports);
        return "airport/airport-list";
    }

    @Override
    public String flightList(Model model) {
        List<TicketDTO>tickets = ticketService.allTicketDTO();
        model.addAttribute("tickets", tickets);
        return "flight/flight-list";
    }
}
