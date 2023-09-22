package com.example.calculadorarest.controller;

import com.example.calculadorarest.dto.ExpressaoDTO;
import com.example.calculadorarest.entity.Expressao;
import com.example.calculadorarest.service.CalculadoraService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Calculadora", description = "A calculadora ira efetuar a operacao enviada e retornar o valor com precisão de 2 dígitos após a vírgula")
@RequestMapping("/api")
public class CalculadoraController {

    CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping(value = "/calculate")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ExpressaoDTO> calculate(@RequestBody Expressao expressao){
        ExpressaoDTO result = calculadoraService.saveExpression(expressao);
        return ResponseEntity.ok(result);
    }
}
