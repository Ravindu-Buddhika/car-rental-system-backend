package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.AdminDTO;
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
    public void registerAdmin(AdminDTO adminDTO) {
        // 1. User (Account) එක හැදීම
        User user = new User();
        user.setEmail(adminDTO.getEmail());
        user.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        user.setRole("ROLE_ADMIN"); // මෙතන Role එක වෙනස්

        User savedUser = userRepository.save(user);

        // 2. Admin (Profile) එක හැදීම
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        admin.setUser(savedUser);

        adminRepository.save(admin);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(admin -> modelMapper.map(admin, AdminDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public AdminDTO getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return modelMapper.map(admin, AdminDTO.class);
    }
    @Override
    public void updateAdminByEmail(String email, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        existingAdmin.setUserName(adminDTO.getUserName());

        existingAdmin.setEmail(adminDTO.getEmail());
        User user = existingAdmin.getUser();
        if (user != null) {
            user.setEmail(adminDTO.getEmail());
            userRepository.save(user);
        }

        adminRepository.save(existingAdmin);
    }
    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        adminRepository.delete(admin);
    }
}
