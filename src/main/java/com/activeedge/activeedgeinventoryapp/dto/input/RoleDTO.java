package com.activeedge.activeedgeinventoryapp.dto.input;

import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private RoleType name;
}
