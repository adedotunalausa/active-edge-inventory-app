package com.activeedge.activeedgeinventoryapp.dto.output;

import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponseDTO implements Serializable {

    @JsonProperty
    protected Status status;

}
