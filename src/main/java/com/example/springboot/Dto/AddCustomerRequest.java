package com.example.springboot.Dto;

import lombok.Data;

@Data
public class AddCustomerRequest {


    private String firstname;
    private String lastname;
    private String nationalid;
    private AccountInfo[] accountInfo;


}
