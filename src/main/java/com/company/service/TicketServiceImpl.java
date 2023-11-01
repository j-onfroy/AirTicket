package com.company.service;

import com.company.dao.TicketDAO;
import com.company.model.Airport;
import com.company.model.Flight;
import com.company.model.Ticket;
import com.company.payload.TicketDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketDAO ticketDAO;
    private final FlightService flightService;
    private final AirportService airportService;

    @Override
    public int saveTicket(Ticket ticket) {
        return ticketDAO.save(ticket);
    }

    @Override
    public List<Ticket> alTicket() {
        return ticketDAO.allAirports();
    }

    @Override
    public List<TicketDTO> allTicketDTO() {
        List<Ticket> tickets = alTicket();
        List<Flight> flights = flightService.allFlights();
        List<Airport> airports = airportService.allAirport();
        Integer flightId;
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : tickets) {
            flightId = ticket.getFlightId();
            Integer finalFlightId = flightId;
            Flight flightDTO = flights.stream().filter(flight -> Objects.equals(flight.getId(), finalFlightId)).findFirst().get();
            Airport fromAirport = airports.stream().filter(airport -> airport.getId().equals(flightDTO.getFromAirportId())).findFirst().get();
            Airport toAirport = airports.stream().filter(airport -> airport.getId().equals(flightDTO.getToAirportId())).findFirst().get();
            String date = flightDTO.getFlightDate();
            String time = flightDTO.getFlightTime();
            TicketDTO ticketDTO = new TicketDTO(ticket.getId(), ticket.getSerialNumber(), ticket.getSeat(), ticket.getPrice(), fromAirport.getName() + ", (" + fromAirport.getCity().substring(0, 3) + "), ".toUpperCase() + fromAirport.getCountry(), toAirport.getName() + ", (" + toAirport.getCity().substring(0, 3) + "), ".toUpperCase() + toAirport.getCountry(), date, time, ticket.isSoldOut());
            ticketDTOList.add(ticketDTO);
        }
        return ticketDTOList;
    }
}
