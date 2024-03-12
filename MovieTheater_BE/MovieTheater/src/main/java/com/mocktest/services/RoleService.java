package com.mocktest.services;

import com.mocktest.dto.RoleDto;
import com.mocktest.entities.Role;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

    public RoleDto getById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        Role role = roleOptional.orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
        return new RoleDto(role);
    }
    public RoleDto create(RoleDto roleDto) {
        Role role = new Role();
        if (roleDto != null) {
            BeanUtils.copyProperties(roleDto, role);
        }
        Role roleSaved = roleRepository.save(role);
        return new RoleDto(roleSaved);
    }
    public RoleDto updateById(RoleDto roleDto, Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        Role role = roleOptional.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        if (roleDto != null) {
            BeanUtils.copyProperties(roleDto, role);
        }
        Role roleUpdated = roleRepository.save(role);
        return new RoleDto(roleUpdated);
    }
    public boolean deleteById(Long id) {
        try {
            if (roleRepository.existsById(id)) {
                roleRepository.deleteById(id);
                return true;
            } else {
                throw new EntityNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user with id: " + id, e);
        }
    }
}
