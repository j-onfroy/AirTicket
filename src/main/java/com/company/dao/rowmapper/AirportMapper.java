package com.company.dao.rowmapper;

import com.company.model.Airport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportMapper implements RowMapper<Airport> {

    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Airport.builder()
                .id(rs.getInt("a_id"))
                .name(rs.getString("a_name"))
                .country(rs.getString("a_country"))
                .city(rs.getString("a_city"))
                .build();
    }
}
