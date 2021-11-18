package com.activeedge.activeedgeinventoryapp.repository;

import com.activeedge.activeedgeinventoryapp.dto.input.RoleDTO;
import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import com.activeedge.activeedgeinventoryapp.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepositoryUnderTest;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        Role role = modelMapper.map(new RoleDTO(RoleType.USER), Role.class);
        roleRepositoryUnderTest.save(role);
    }

    @Test
    void shouldFindRoleByName() {
        Optional<Role> role = roleRepositoryUnderTest.findByName(RoleType.USER);

        assert(role.isPresent());
    }
}