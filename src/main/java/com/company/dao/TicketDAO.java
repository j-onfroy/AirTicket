package com.company.dao;

import com.company.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ticket> allAirports() {
        //   SqlParameterSource parameterSource = new MapSqlParameterSource()
        return jdbcTemplate.query("SELECT * FROM ticket", (rs, rowNum) -> {
            Ticket ticket = new Ticket();
            ticket.setSerialNumber(rs.getString(2));
            ticket.setSeat(rs.getString(3));
            ticket.setPrice(rs.getInt(4));
            ticket.setFlightId(rs.getInt(5));
            ticket.setSoldOut(rs.getBoolean(6));
            return ticket;
        });
    }

    public int save(Ticket ticket) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("serial", ticket.getSerialNumber())
                .addValue("seat", ticket.getSeat())
                .addValue("price", ticket.getPrice())
                .addValue("flight_id", ticket.getFlightId())
                .addValue("sold_out", ticket.isSoldOut());

        return jdbcTemplate.update("""
                INSERT INTO ticket(serial_number,seat,price,flight_id,sold_out)
                VALUES (:serial,:seat,:price,:flight_id,:sold_out)
                """, parameterSource
        );
    }
}
