package com.example.springboot.controller;

import com.example.springboot.Dto.*;
import com.example.springboot.Repository.AccountRepo;
import com.example.springboot.Repository.CustomerRepo;
import com.example.springboot.entity.CustomerEntity;
import com.example.springboot.service.WebService;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class WebController {

	private final CustomerRepo customerRepo;

	private  final WebService webService;

	private final AccountRepo accountRepo;

	public WebController(WebService webService, CustomerRepo customerRepo,AccountRepo accountRepo) {
		this.webService = webService;
		this.customerRepo = customerRepo;
		this.accountRepo = accountRepo;

	}


	@GetMapping("/Customer/allUser")
	public List<CustomerEntity> getAllPERSON(){
		return customerRepo.findAll();
	}

	@PostMapping("/Customer/add")
	public CustomerResponse addCustomer(@RequestBody AddCustomerRequest addCustomerRequest){

		return webService.addCustomer(addCustomerRequest);

	}

	@PostMapping("/Customer/get")
	public CustomerInfoResponse getCustomerInfoByNationalID(@RequestBody GetCustomerRequest getCustomerRequest){
		return webService.getCustomerInfoByNationalID(getCustomerRequest);

	}

	@PostMapping("/Customer/update")
	public UpdateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return webService.updateCustomer(updateCustomerRequest);
	}

	@DeleteMapping("/Customer/delete/{id}")
	public CustomerResponse deleteCustomer(DeleteCustomerRequest deleteCustomerRequest){
		return webService.deleteCustomer(deleteCustomerRequest);
	}


}
