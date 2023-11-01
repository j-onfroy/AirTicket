package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private String passport;
    private String country;
}
