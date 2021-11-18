package com.activeedge.activeedgeinventoryapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "stocks")
public class Stock extends BaseModel{

    private String name;

    @OneToOne(targetEntity = Amount.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "stock_amount",
            joinColumns = { @JoinColumn(name = "stock_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "amount_id", referencedColumnName = "id") }
    )
    private Amount currentPrice;

    private Long quantityInStock;

    private Long reorderLevel;

    private boolean isDiscontinued;

}
