package org.example.service;

import org.example.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void registerCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
}
