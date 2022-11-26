package com.example.springboot.service;
import com.example.springboot.Dto.*;
import com.example.springboot.Exception.ResourceNotFoundException;
import com.example.springboot.Repository.AccountRepo;
import com.example.springboot.Repository.CustomerRepo;
import com.example.springboot.entity.AccountEntity;
import com.example.springboot.entity.CustomerEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    AccountRepo accountRepo;

    CustomerRepo CustomerRepo;


    public WebServiceImpl(CustomerRepo CustomerRepo,AccountRepo accountRepo) {
        this.CustomerRepo = CustomerRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public CustomerResponse addCustomer(AddCustomerRequest addCustomerRequest) {


        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerEntity.getId());
        customerEntity.setFirstName(addCustomerRequest.getFirstname());
        customerEntity.setLastName(addCustomerRequest.getLastname());
        customerEntity.setNationalId(addCustomerRequest.getNationalid());




        CustomerRepo.save(customerEntity);

        for (AccountInfo accountInfo: addCustomerRequest.getAccountInfo()) {

            AccountEntity  accountEntity = new AccountEntity();

            accountEntity.setId(accountEntity.getId());
            accountEntity.setAccountType(accountInfo.getAccountType());
            accountEntity.setAccountNumber(accountInfo.getAccountNumber());
            accountEntity.setAccount_Fk(customerEntity.getNationalId());
            accountRepo.save(accountEntity);

        }
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setResult("ok");
        return customerResponse;
    }

    @Override
    public CustomerInfoResponse getCustomerInfoByNationalID(GetCustomerRequest getCustomerRequest) {

       List<CustomerEntity> lstCustomer= CustomerRepo.findByNationalId(getCustomerRequest.getNationalId());

        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();

        for (CustomerEntity customer: lstCustomer){

            customerInfoResponse.setFirstname(customer.getFirstName());
            customerInfoResponse.setLastname(customer.getLastName());

            ArrayList<AccountInfo> infoArrayList = new ArrayList<>();

            for (AccountEntity accountEntity : customer.getAccountEntitySet()){
                AccountInfo account = new AccountInfo();

                account.setAccountNumber(accountEntity.getAccountNumber());
                account.setAccountType(accountEntity.getAccountType());
                infoArrayList.add(account);
            }
            customerInfoResponse.setAccountInfos(infoArrayList);

        }

		return customerInfoResponse;

    }

    @Override

    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) {

        CustomerEntity person = CustomerRepo.findById(updateCustomerRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + updateCustomerRequest.getId()));


        person.setId(updateCustomerRequest.getId());
        person.setFirstName(updateCustomerRequest.getFirstname());
        person.setLastName(updateCustomerRequest.getLastname());
        person.setNationalId(updateCustomerRequest.getNationalId());

        CustomerEntity save1 = CustomerRepo.save(person);


            UpdateCustomerResponse updateCustomerResponse = new UpdateCustomerResponse();
            updateCustomerResponse.setId(person.getId());
            updateCustomerResponse.setFirstname(person.getFirstName());
            updateCustomerResponse.setLastname(person.getLastName());
            updateCustomerResponse.setNationalId(person.getNationalId());
            return updateCustomerResponse;

    }

    @Override
    public CustomerResponse deleteCustomer(@PathVariable DeleteCustomerRequest userRequest) {
        CustomerEntity person = CustomerRepo.findById(userRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person Not Exist With id: " + userRequest.getId()));

        CustomerRepo.delete(person);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setResult("Delete");
        return customerResponse;

    }

}
