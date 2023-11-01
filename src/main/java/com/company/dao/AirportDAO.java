package com.company.dao;

import com.company.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirportDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AirportDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Airport> allAirports() {
        //   SqlParameterSource parameterSource = new MapSqlParameterSource()
        return jdbcTemplate.query("SELECT * FROM airports", (rs, rowNum) -> {
            Airport airport = new Airport();
            airport.setId(rs.getInt(1));
            airport.setName(rs.getString(2));
            airport.setCountry(rs.getString(3));
            airport.setCity(rs.getString(4));
            return airport;
        });
    }

    public void save(Airport airport) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", airport.getName())
                .addValue("country", airport.getCountry())
                .addValue("city",airport.getCity());

        jdbcTemplate.update("""
                INSERT INTO airports(name,country,city) VALUES(:name,:country,:city)
                """, parameterSource
        );
    }
}
