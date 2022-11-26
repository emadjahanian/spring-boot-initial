package com.example.springboot.Dto;

import lombok.Data;


@Data
public class GetCustomerRequest {
    private String NationalId;


    @Override
    public String toString() {
        return "GetCustomerRequest{" +
                "NationalId='" + NationalId + '\'' +
                '}';
    }
}
