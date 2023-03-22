package com.example.demo.service.impl;

import com.example.demo.data.entity.Role;
import com.example.demo.data.enums.ERole;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(ERole.getRoleByName(name))
                .orElseThrow(() -> new RuntimeException("Role with value " + name + " is not found in DataBase"));
    }
}
