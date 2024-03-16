package com.mocktest.services;

import com.mocktest.entities.Role;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public  Role getById(Long id) {
            Optional<Role> roleOptional = roleRepository.findById(id);
            Role role = roleOptional.orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
            return role;
    }
    public Role create(Role request) {
            return roleRepository.save(request);
    }
    public Role updateById(Role request, Long id) {
            Optional<Role> roleOptional = roleRepository.findById(id);
            Role role = roleOptional.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
            BeanUtils.copyProperties(request, role);
            return roleRepository.save(role);
    }
    public void deleteById(Long request) {
            if (roleRepository.existsById(request)) {
                roleRepository.deleteById(request);
            } else {
                throw new NotFoundException("User not found with id: " + request);
            }
    }
}
