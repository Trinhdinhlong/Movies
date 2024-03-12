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
    public RoleDto create(RoleDto request) {
            Role role = new Role();
            BeanUtils.copyProperties(request, role);
            return new RoleDto(roleRepository.save(role));
    }
    public RoleDto updateById(RoleDto roleDto, Long id) {
            Optional<Role> roleOptional = roleRepository.findById(id);
            Role role = roleOptional.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
            BeanUtils.copyProperties(roleDto, role);
            return new RoleDto(roleRepository.save(role));
    }
    public void deleteById(Long request) {
            if (roleRepository.existsById(request)) {
                roleRepository.deleteById(request);
            } else {
                throw new NotFoundException("User not found with id: " + request);
            }
    }
}
