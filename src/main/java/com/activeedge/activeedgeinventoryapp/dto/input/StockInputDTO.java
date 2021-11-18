package com.activeedge.activeedgeinventoryapp.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockInputDTO {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @JsonProperty("current_price")
    @NotNull(message = "current price cannot be empty")
    private AmountInputDTO currentPrice;

    @JsonProperty("quantity_in_stock")
    private Long quantityInStock;

    @JsonProperty("reorder_level")
    private Long reorderLevel;

    @JsonProperty("is_discontinued")
    private boolean isDiscontinued;

}
