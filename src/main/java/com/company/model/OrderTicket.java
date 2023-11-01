package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTicket {
    private Integer id;
    private Integer customerId;
    private Integer ticketId;
}
