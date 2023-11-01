package com.company.service;

import com.company.model.Airport;

import java.util.List;

public interface AirportService {
    void saveAirport(Airport airport);
    List<Airport> allAirport();
    List<String> allAirportName();
}
