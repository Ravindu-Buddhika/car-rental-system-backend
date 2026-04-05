package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.CustomerDTO;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @GetMapping("/search/user/{userId}")
    public ResponseEntity<CustomerDTO> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(customerService.getCustomerByUserId(userId));
    }
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(Authentication authentication, @RequestBody CustomerDTO dto) {
        String email = authentication.getName(); // මේකෙන් කෙලින්ම email එක එනවා
        customerService.updateCustomerByEmail(email, dto);
        return ResponseEntity.ok("Profile updated successfully!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer and associated user account deleted successfully!");
    }
}
