package com.activeedge.activeedgeinventoryapp.service.serviceImplementation;

import com.activeedge.activeedgeinventoryapp.dto.input.LoginDTO;
import com.activeedge.activeedgeinventoryapp.dto.input.SignupDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.JwtResponseDTO;
import com.activeedge.activeedgeinventoryapp.enums.Gender;
import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.activeedge.activeedgeinventoryapp.exception.ResourceNotFoundException;
import com.activeedge.activeedgeinventoryapp.model.Role;
import com.activeedge.activeedgeinventoryapp.model.User;
import com.activeedge.activeedgeinventoryapp.repository.RoleRepository;
import com.activeedge.activeedgeinventoryapp.repository.UserRepository;
import com.activeedge.activeedgeinventoryapp.security.service.UserDetailsImpl;
import com.activeedge.activeedgeinventoryapp.service.UserService;
import com.activeedge.activeedgeinventoryapp.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public BasicResponseDTO registerUser(SignupDTO userDetails) {

        if (emailExists(userDetails.getEmail())) {
            return new BasicResponseDTO(Status.FAILED_VALIDATION, "Email is already in use");
        }

        if (usernameExists(userDetails.getUsername())) {
            return new BasicResponseDTO(Status.FAILED_VALIDATION, "Username is already taken");
        }

        User newUser = createUserObjectFromSignupInfo(userDetails);

        setRolesForNewUser(newUser);

        userRepository.save(newUser);

        return new BasicResponseDTO(Status.CREATED, newUser);

    }

    @Override
    public BasicResponseDTO authenticateUser(LoginDTO userDetails) {

        if (!emailExists(userDetails.getEmail())) {
            return new BasicResponseDTO(Status.NOT_FOUND, "Error: User not found! Make sure email is correct " +
                    "or signup if you don't have an account");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userInfo = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userInfo.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new BasicResponseDTO(Status.SUCCESS, new JwtResponseDTO(jwt, userInfo.getUsername(), roles));

    }

    private User createUserObjectFromSignupInfo(SignupDTO userDetails) {
        return new User(
                userDetails.getUsername(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                Gender.getByAlias(userDetails.getGender()),
                userDetails.getEmail(),
                passwordEncoder.encode(userDetails.getPassword())
        );
    }

    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private void setRolesForNewUser(User newUser) {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role not found."));
        roles.add(userRole);
        newUser.setRoles(roles);
    }

}
