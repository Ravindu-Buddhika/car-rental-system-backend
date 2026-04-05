package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.CustomerDTO;
import org.example.model.entity.Customer;
import org.example.model.entity.User;
import org.example.repository.CustomerRepository;
import org.example.repository.UserRepository;
import org.example.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerCustomer(CustomerDTO customerDTO) {
        User user = new User();
        user.setEmail(customerDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_CUSTOMER");

        User savedUser = userRepository.save(user);

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        customer.setUser(savedUser);

        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Customer customer = customerRepository.findByNic(nic)
                .orElseThrow(() -> new RuntimeException("Customer not found with NIC: " + nic));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByUserId(Long userId) {
        Customer customer = customerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found for User ID: " + userId));
        return modelMapper.map(customer, CustomerDTO.class);
    }
    @Override
    public void updateCustomerByEmail(String email, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

        existingCustomer.setFullName(customerDTO.getFullName());
        existingCustomer.setContactNumber(customerDTO.getContactNumber());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setDrivingLicenseNumber(customerDTO.getDrivingLicenseNumber());
        existingCustomer.setNic(customerDTO.getNic());
        existingCustomer.setEmail(customerDTO.getEmail());

        User linkedUser = existingCustomer.getUser();
        if (linkedUser != null) {
            linkedUser.setEmail(customerDTO.getEmail());

            userRepository.save(linkedUser);
        }

        customerRepository.save(existingCustomer);
    }
    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        User user = customer.getUser();

        customerRepository.delete(customer);

        if (user != null) {
            userRepository.delete(user);
        }
    }
}
