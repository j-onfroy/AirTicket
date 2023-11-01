package com.company.dao.rowmapper;

import com.company.model.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Flight.builder()
                .id(rs.getInt(1))
                .toAirportId(rs.getInt(2))
                .countOfSeats(rs.getInt(3))
                .flightTime(rs.getString(4))
                .flightDate(rs.getString(5))
                .fromAirportId(rs.getInt(6))
                .build();
    }
}
