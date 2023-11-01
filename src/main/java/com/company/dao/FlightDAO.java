package com.company.dao;

import com.company.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public Integer save(Flight flight){
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("to_airport_id",flight.getToAirportId())
                .addValue("from_airport_id",flight.getFromAirportId())
                .addValue("count_of_seats",flight.getCountOfSeats())
                .addValue("flight_time",flight.getFlightTime())
                .addValue("flight_date",flight.getFlightDate());
        return jdbcTemplate.queryForObject("""
                INSERT INTO "flight"(to_airport_id,count_of_seats,flight_time,flight_date,from_airport_id)
                VALUES (:to_airport_id,:count_of_seats,:flight_time,:flight_date,:from_airport_id) returning id
                """,parameterSource, Integer.class);
    }

    public List<Flight> allFlights() {
        return jdbcTemplate.query("SELECT * FROM flight", (rs, rowNum) -> {
                    Flight flight = new Flight();
                    flight.setId(rs.getInt(1));
                    flight.setToAirportId(rs.getInt(2));
                    flight.setCountOfSeats(rs.getInt(3));
                    flight.setFlightTime(rs.getString(4));
                    flight.setFromAirportId(rs.getInt(5));
                    flight.setFlightDate(rs.getString(6));
                    return flight;
                });
    }
}
