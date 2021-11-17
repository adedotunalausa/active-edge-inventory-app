package com.activeedge.activeedgeinventoryapp.controller;

import com.activeedge.activeedgeinventoryapp.dto.input.LoginDTO;
import com.activeedge.activeedgeinventoryapp.dto.input.SignupDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;
import com.activeedge.activeedgeinventoryapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController extends Controller {

    private final UserService userService;

    @PostMapping("/login")
    public BasicResponseDTO login(@Valid @RequestBody LoginDTO userDetails) {
        return responseWithUpdatedHttpStatus(userService.authenticateUser(userDetails));
    }

    @PostMapping("/signup")
    public BasicResponseDTO register(@Valid @RequestBody SignupDTO userDetails) {
        return responseWithUpdatedHttpStatus(userService.registerUser(userDetails));
    }
}
