package com.shekhovtsov.auth.service;

import com.shekhovtsov.auth.dao.RoleRepository;
import com.shekhovtsov.auth.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}