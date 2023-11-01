package com.company.dao.rowmapper;

import com.company.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ticket.builder()
                .id(rs.getInt("t_id"))
                .serialNumber(rs.getString("t_serial_number"))
                .seat(rs.getString("t_seat"))
                .price(rs.getInt("t_price"))
                .flightId(rs.getInt("t_flight_id"))
                .soldOut(rs.getBoolean("t_sold_out"))
                .build();

    }
}
