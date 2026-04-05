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
@Transactional // ඉතාම වැදගත්!
@RequiredArgsConstructor // Lombok පාවිච්චි කරලා Repositories ටික Inject කරගන්න
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerCustomer(CustomerDTO customerDTO) {
        // 1. මුලින්ම User Object එක හදාගන්න (Authentication විස්තර)
        User user = new User();
        user.setEmail(customerDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_CUSTOMER");

        // 2. User ව Save කරන්න
        User savedUser = userRepository.save(user);

        // 3. දැන් Customer Object එක හදාගන්න (Profile විස්තර)
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        // 4. අර Save කරපු User ව Customer ට සම්බන්ධ කරන්න (Linking)
        customer.setUser(savedUser);

        // 5. අවසාන වශයෙන් Customer ව Save කරන්න
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
        // User table එක හරහා find කරන එක
        Customer customer = customerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found for User ID: " + userId));
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
