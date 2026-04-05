package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.CustomerDTO;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin // Frontend එකෙන් එන requests වලට ඉඩ දෙන්න
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.ok("Customer registered successfully!");
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/search/nic/{nic}")
    public ResponseEntity<CustomerDTO> getByNic(@PathVariable String nic) {
        return ResponseEntity.ok(customerService.getCustomerByNic(nic));
    }
}
