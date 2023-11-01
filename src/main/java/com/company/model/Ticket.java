package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    private Integer id;
    private String serialNumber;
    private String seat;
    private Integer price;
    private Integer flightId;
    private boolean soldOut;
}
