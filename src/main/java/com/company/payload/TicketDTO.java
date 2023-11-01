package com.company.payload;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class TicketDTO{
        private Integer id;
        private String serialNumber;
        private String seat;
        private Integer price;
        private String fromAirport;
        private String toAirport;
        private String date;
        private String time;
        private Boolean soldOut;
}
