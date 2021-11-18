package com.activeedge.activeedgeinventoryapp.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
public class AmountInputDTO {

    @NotBlank(message = "currency cannot be empty")
    private String currency;

    private double value;

}
