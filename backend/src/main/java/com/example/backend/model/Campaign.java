package com.example.backend.model;

import jakarta.persistence.*;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid

    @NotNull(message = "Campaign name is mandatory")
    @NotBlank(message = "Campaign name is mandatory")
    private String campaignName;

    @NotNull(message = "Campaign name is mandatory")
    @NotBlank(message = "Keywords are mandatory")
    private String keywords;

    @NotNull(message = "Bid amount is mandatory")
    @Min(value = 0, message = "Bid amount must be greater than or equal to 0")
    private Double bidAmount;

    @NotNull(message = "Campaign fund is mandatory")
    @Min(value = 0, message = "Campaign fund must be greater than or equal to 0")
    private Double campaignFund;

    @NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotNull(message = "Town is mandatory")
    private Town town;

    @NotNull(message = "Radius is mandatory")
    @Min(value = 1, message = "Radius must be greater than 0")
    private Integer radius;
}
