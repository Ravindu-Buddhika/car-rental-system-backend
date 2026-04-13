package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.AdminRequestDTO;
import org.example.model.dto.response.AdminResponseDTO;
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
    public ResponseEntity<AdminResponseDTO> registerAdmin(@RequestBody AdminRequestDTO adminDTO) {
        return ResponseEntity.ok(adminService.registerAdmin(adminDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<AdminResponseDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.getAdminByEmail(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<AdminResponseDTO> updateProfile(Authentication authentication, @RequestBody AdminRequestDTO dto) {
        String email = authentication.getName();
        return ResponseEntity.ok(adminService.updateAdminByEmail(email, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin and associated user account deleted successfully!");
    }
}