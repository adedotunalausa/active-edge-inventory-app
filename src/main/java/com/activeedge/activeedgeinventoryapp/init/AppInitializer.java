package com.activeedge.activeedgeinventoryapp.init;

import com.activeedge.activeedgeinventoryapp.dto.input.RoleDTO;
import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import com.activeedge.activeedgeinventoryapp.model.Role;
import com.activeedge.activeedgeinventoryapp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role;

        if (roleRepository.findByName(RoleType.ADMIN).isEmpty()) {
            role = modelMapper.map(new RoleDTO(RoleType.ADMIN), Role.class);
            roleRepository.save(role);
        }

        if (roleRepository.findByName(RoleType.USER).isEmpty()) {
            role = modelMapper.map(new RoleDTO(RoleType.USER), Role.class);
            roleRepository.save(role);
        }
    }
}
