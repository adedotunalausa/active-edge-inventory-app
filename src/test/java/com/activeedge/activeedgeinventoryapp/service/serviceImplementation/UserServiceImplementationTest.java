package com.activeedge.activeedgeinventoryapp.service.serviceImplementation;

import com.activeedge.activeedgeinventoryapp.dto.input.SignupDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import com.activeedge.activeedgeinventoryapp.model.Role;
import com.activeedge.activeedgeinventoryapp.repository.RoleRepository;
import com.activeedge.activeedgeinventoryapp.repository.UserRepository;
import com.activeedge.activeedgeinventoryapp.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserRepository userRepository;

    @Mock private RoleRepository roleRepository;

    @Mock private AuthenticationManager authenticationManager;

    @Mock private JwtUtils jwtUtils;

    private UserServiceImplementation userServiceImplementationUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplementationUnderTest = new UserServiceImplementation(userRepository, roleRepository,
                authenticationManager, passwordEncoder, jwtUtils);
    }

    @Test
    void canRegisterUser() {
        // given
        SignupDTO dto = getSignupDTO();

        Role role = new Role(2, RoleType.USER);
        given(roleRepository.findByName(RoleType.USER)).willReturn(Optional.of(role));

        // when
        lenient().when(userRepository.existsByEmail("adedotunalausa@gmail.com")).thenReturn(false);
        lenient().when(userRepository.existsByUsername("demarxes")).thenReturn(false);
        BasicResponseDTO response = userServiceImplementationUnderTest.registerUser(dto);

        // then
        assertThat(response.getResponseData()).isNotNull();

    }

    private SignupDTO getSignupDTO() {
        return new SignupDTO("demarxes", "Adedotun", "Alausa",
                "Male", "adedotunalausa@gmail.com", "123456");
    }

}