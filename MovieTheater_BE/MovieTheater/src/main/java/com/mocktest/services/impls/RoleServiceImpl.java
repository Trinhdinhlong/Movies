package com.mocktest.services.impls;

import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.entities.User;
import com.mocktest.repository.RoleRepository;
import com.mocktest.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleDto> getAll() {
        return null;
    }

    @Override
    public RoleDto getById(Long id) {
        Optional<Role> roleDto = roleRepository.findById(id);
        Role role = roleDto.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return new RoleDto(role);
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        return null;
    }
}
