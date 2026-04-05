package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.AdminDTO;
import org.example.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.registerAdmin(adminDTO);
        return ResponseEntity.ok("Admin registered successfully!");
    }
}
