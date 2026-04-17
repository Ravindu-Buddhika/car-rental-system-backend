package org.example.service;

import org.example.model.dto.CustomerDTO;
import org.example.model.dto.request.CustomerRequestDTO;
import org.example.model.dto.response.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO registerCustomer(CustomerRequestDTO dto);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO getCustomerByNic(String nic);
    CustomerResponseDTO getCustomerByUserId(Long userId);
    CustomerResponseDTO updateCustomerByEmail(String email, CustomerRequestDTO dto);
    void deleteCustomer(Long id);
    CustomerResponseDTO getCustomerByEmail(String email);
}
