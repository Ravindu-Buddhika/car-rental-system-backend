package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.AdminDTO;
import org.example.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @GetMapping("/search/email/{email}")
    public ResponseEntity<AdminDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.getAdminByEmail(email));
    }
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(Authentication authentication, @RequestBody AdminDTO dto) {
        String email = authentication.getName();
        adminService.updateAdminByEmail(email, dto);
        return ResponseEntity.ok("Admin profile updated successfully!");
    }
}
