package com.example.springboot.service;

import com.example.springboot.Dto.*;

public interface WebService {

    CustomerResponse addCustomer(AddCustomerRequest userRequest);
    CustomerInfoResponse getCustomerInfoByNationalID(GetCustomerRequest userRequest);
    UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest);
    CustomerResponse deleteCustomer(DeleteCustomerRequest userRequest);

}
