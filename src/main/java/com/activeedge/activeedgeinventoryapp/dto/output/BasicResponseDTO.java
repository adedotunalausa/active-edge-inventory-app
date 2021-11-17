package com.activeedge.activeedgeinventoryapp.dto.output;

import com.activeedge.activeedgeinventoryapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BasicResponseDTO extends StandardResponseDTO {

    @JsonProperty
    private Object responseData;

    public BasicResponseDTO(Status status) {
        super(status);
    }

    public BasicResponseDTO(Status status, Object responseData) {
        super(status);
        this.responseData = responseData;
    }

}
