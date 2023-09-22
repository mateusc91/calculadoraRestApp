package com.example.calculadorarest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
public class ExpressaoDTO {
    private BigDecimal resultado;
}
