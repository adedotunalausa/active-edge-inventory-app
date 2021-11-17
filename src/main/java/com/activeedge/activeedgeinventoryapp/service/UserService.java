package com.activeedge.activeedgeinventoryapp.service;

import com.activeedge.activeedgeinventoryapp.dto.input.LoginDTO;
import com.activeedge.activeedgeinventoryapp.dto.input.SignupDTO;
import com.activeedge.activeedgeinventoryapp.dto.output.BasicResponseDTO;

public interface UserService {

    BasicResponseDTO registerUser(SignupDTO userDetails);

    BasicResponseDTO authenticateUser(LoginDTO userDetails);

}
