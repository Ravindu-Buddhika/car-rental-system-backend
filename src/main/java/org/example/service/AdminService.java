package org.example.service;

import org.example.model.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    public void registerAdmin(AdminDTO adminDTO);
    List<AdminDTO> getAllAdmins();
}
