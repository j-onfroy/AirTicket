package com.company.service;

import com.company.dao.AirportDAO;
import com.company.model.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportDAO airportDAO;

    @Override
    public void saveAirport(Airport airport) {
        airportDAO.save(airport);
    }

    @Override
    public List<Airport> allAirport() {
        return airportDAO.allAirports();
    }

    @Override
    public List<String> allAirportName() {
        List<Airport> airports = airportDAO.allAirports();
        return airports.stream().map(airport -> airport.getName() + ", (" + airport.getCity().substring(0, 3).toUpperCase() + "), " + airport.getCountry()).toList();
    }
}
