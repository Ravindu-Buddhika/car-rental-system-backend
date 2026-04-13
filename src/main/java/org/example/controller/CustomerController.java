package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.CustomerRequestDTO;
import org.example.model.dto.response.CustomerResponseDTO;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> registerCustomer(@RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(customerService.registerCustomer(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/nic/{nic}")
    public ResponseEntity<CustomerResponseDTO> getByNic(@PathVariable String nic) {
        return ResponseEntity.ok(customerService.getCustomerByNic(nic));
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity<CustomerResponseDTO> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(customerService.getCustomerByUserId(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<CustomerResponseDTO> updateProfile(Authentication authentication, @RequestBody CustomerRequestDTO dto) {
        String email = authentication.getName();
        return ResponseEntity.ok(customerService.updateCustomerByEmail(email, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully!");
    }
}