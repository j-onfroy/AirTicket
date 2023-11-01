package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Flight {
    private Integer id;
    private Integer fromAirportId;
    private Integer toAirportId;
    private String flightTime;
    private String flightDate;
    private Integer countOfSeats;
}
