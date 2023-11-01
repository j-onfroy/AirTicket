package com.company.service;

import com.company.model.Flight;
import com.company.model.Ticket;

import java.util.List;

public interface FlightService {
    Integer save(Flight flight);

    void save(String flightTime,
              String flightDate,
              String countOfSeats,
              String fromAirportId,
              String toAirportId);

    List<Flight> allFlights();

    List<Ticket> createTicket(Flight flight,Integer flightId);
}
