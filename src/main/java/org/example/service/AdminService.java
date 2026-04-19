package org.example.service;

import org.example.model.dto.AdminDTO;
import org.example.model.dto.request.AdminRequestDTO;
import org.example.model.dto.response.AdminResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO registerAdmin(AdminRequestDTO adminDTO);
    List<AdminResponseDTO> getAllAdmins();
    AdminResponseDTO getAdminByEmail(String email);
    AdminResponseDTO updateAdminByEmail(String email, AdminRequestDTO adminDTO);
    void deleteAdmin(Long id);
}
