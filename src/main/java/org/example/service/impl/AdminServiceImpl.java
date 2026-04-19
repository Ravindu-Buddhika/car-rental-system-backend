package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.AdminRequestDTO;
import org.example.model.dto.response.AdminResponseDTO;
import org.example.model.entity.Admin;
import org.example.model.entity.User;
import org.example.repository.AdminRepository;
import org.example.repository.UserRepository;
import org.example.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDTO registerAdmin(AdminRequestDTO adminDTO) {
        // 1. මුලින්ම Security එක සඳහා User Account එක හදාගන්නවා
        User user = new User();
        user.setEmail(adminDTO.getEmail());
        user.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        user.setRole("ROLE_ADMIN");

        User savedUser = userRepository.save(user);

        Admin admin = new Admin();
        admin.setUserName(adminDTO.getUserName());
        admin.setEmail(adminDTO.getEmail());
        admin.setEmployeeId(adminDTO.getEmployeeId());
        admin.setUser(savedUser);

        Admin savedAdmin = adminRepository.save(admin);

        return modelMapper.map(savedAdmin, AdminResponseDTO.class);
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(admin -> modelMapper.map(admin, AdminResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return modelMapper.map(admin, AdminResponseDTO.class);
    }

    @Override
    public AdminResponseDTO updateAdminByEmail(String email, AdminRequestDTO adminDTO) {
        Admin existingAdmin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        existingAdmin.setUserName(adminDTO.getUserName());
        existingAdmin.setEmployeeId(adminDTO.getEmployeeId());

        existingAdmin.setEmail(adminDTO.getEmail());
        User user = existingAdmin.getUser();
        if (user != null) {
            user.setEmail(adminDTO.getEmail());
            userRepository.save(user);
        }

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return modelMapper.map(updatedAdmin, AdminResponseDTO.class);
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        User user = admin.getUser();
        adminRepository.delete(admin);

        if (user != null) {
            userRepository.delete(user);
        }
    }
}