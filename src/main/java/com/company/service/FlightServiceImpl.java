package com.company.service;

import com.company.dao.FlightDAO;
import com.company.model.Airport;
import com.company.model.Flight;
import com.company.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightDAO flightDAO;
    private final AirportService airportService;
    private final TicketService ticketService;

    @Autowired
    public FlightServiceImpl(@Lazy FlightDAO flightDAO, @Lazy AirportService airportService, @Lazy TicketService ticketService) {
        this.flightDAO = flightDAO;
        this.airportService = airportService;
        this.ticketService = ticketService;
    }

    @Override
    public Integer save(Flight flight) {
        return flightDAO.save(flight);
    }

    @Override
    public void save(String flightTime, String flightDate, String countOfSeats, String fromAirport, String toAirport) {
        List<Airport> airports = airportService.allAirport();


        String[] fromParts = fromAirport.split(", ");
        String[] toParts = toAirport.split(", ");

        String fromName = fromParts[0];
        String fromCountry = fromParts[2];

        String toName = toParts[0];
        String toCountry = toParts[2];

        Integer fromAirportId = airports.stream().filter(airport -> airport.getName().equals(fromName) && airport.getCountry().equals(fromCountry)).findFirst().orElse(null).getId();
        Integer toAirportId = airports.stream().filter(airport -> airport.getName().equals(toName) && airport.getCountry().equals(toCountry)).findFirst().orElse(null).getId();

        Flight flight = Flight.builder()
                .fromAirportId(fromAirportId)
                .toAirportId(toAirportId)
                .flightTime(flightTime)
                .flightDate(flightDate)
                .countOfSeats(Integer.valueOf(countOfSeats))
                .build();

        Integer flightId = save(flight);
        createTicket(flight, flightId);
    }

    @Override
    public List<Flight> allFlights() {
        return flightDAO.allFlights();
    }


    @Override
    public List<Ticket> createTicket(Flight flight, Integer flightId) {
        List<Ticket> tickets = new ArrayList<>();
        String currentSerial = "AA000000";
        int totalSeats = flight.getCountOfSeats();

        for (int i = 0; i < totalSeats; i++) {
            int price = 49;
            if(i%6==0){
                price = price + 10;
            }
            Ticket ticket = Ticket.builder()
                    .serialNumber(currentSerial)
                    .seat(String.valueOf(i))
                    .price(price)
                    .flightId(flightId)
                    .soldOut(false)
                    .build();
            tickets.add(ticket);
            ticketService.saveTicket(ticket);


            currentSerial = incrementSerial(currentSerial);
        }

        return tickets;
    }

    private String incrementSerial(String serial) {
        char[] serialChars = serial.toCharArray();

        for (int i = serialChars.length - 1; i >= 2; i--) {
            if (serialChars[i] < '9') {
                serialChars[i]++;
                break;
            } else {
                serialChars[i] = '0';
            }
        }

        if (serialChars[7] == '9' && serialChars[6] == 'Z' && serialChars[5] == 'Z') {
            return "Serial number limit exceeded";
        }

        for (int i = 5; i >= 0; i--) {
            if (serialChars[i] < 'Z') {
                serialChars[i]++;
                break;
            } else {
                serialChars[i] = 'A';
            }
        }

        return new String(serialChars);
    }

}
