package com.example.springboot.Dto;
import lombok.Data;
import java.util.List;


@Data
public class CustomerInfoResponse {

    private String firstname;
    private String lastname;
    private List<AccountInfo> accountInfos;

}
