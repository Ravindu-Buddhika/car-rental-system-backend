package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.CustomerRequestDTO;
import org.example.model.dto.response.CustomerResponseDTO;
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
    public CustomerResponseDTO registerCustomer(CustomerRequestDTO dto) {
        // 1. User Account එක හැදීම
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_CUSTOMER");
        User savedUser = userRepository.save(user);

        // 2. Customer Profile එක හැදීම
        Customer customer = modelMapper.map(dto, Customer.class);
        customer.setUser(savedUser);

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponseDTO(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerByNic(String nic) {
        Customer customer = customerRepository.findByNic(nic)
                .orElseThrow(() -> new RuntimeException("Customer not found with NIC: " + nic));
        return mapToResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO getCustomerByUserId(Long userId) {
        Customer customer = customerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found for User ID: " + userId));
        return mapToResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomerByEmail(String email, CustomerRequestDTO dto) {
        Customer existingCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

        // Field updates
        existingCustomer.setFullName(dto.getFullName());
        existingCustomer.setContactNumber(dto.getContactNumber());
        existingCustomer.setAddress(dto.getAddress());
        existingCustomer.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        existingCustomer.setNic(dto.getNic());
        existingCustomer.setEmail(dto.getEmail());

        // User Table update
        if (existingCustomer.getUser() != null) {
            existingCustomer.getUser().setEmail(dto.getEmail());
            userRepository.save(existingCustomer.getUser());
        }

        return mapToResponseDTO(customerRepository.save(existingCustomer));
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        User user = customer.getUser();
        customerRepository.delete(customer);
        if (user != null) userRepository.delete(user);
    }

    // ID එක පැටලෙන නිසා හදපු Helper Method එක
    private CustomerResponseDTO mapToResponseDTO(Customer customer) {
        CustomerResponseDTO resp = modelMapper.map(customer, CustomerResponseDTO.class);
        resp.setId(customer.getCustomerId()); // Entity එකේ customerId එක DTO එකේ id එකට සෙට් කරනවා
        return resp;
    }
}