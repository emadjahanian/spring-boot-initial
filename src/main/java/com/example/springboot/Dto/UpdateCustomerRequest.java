package com.example.springboot.Dto;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private long id;
    private String firstname;
    private String lastname;
    private String nationalId;
    private String AccountType;
    private String AccountNumber;
}
