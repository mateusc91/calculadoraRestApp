package com.example.calculadorarest.service;

import com.example.calculadorarest.dto.ExpressaoDTO;
import com.example.calculadorarest.entity.Expressao;
import com.example.calculadorarest.repository.CalculadoraRepo;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.apache.el.lang.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.beans.Expression;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class CalculadoraService {

    CalculadoraRepo calculadoraRepo;

    ExpressaoDTO response;

    public CalculadoraService(CalculadoraRepo calculadoraRepo) {
        this.calculadoraRepo = calculadoraRepo;
    }

    public ExpressaoDTO saveExpression(Expressao expressao) {
        calculadoraRepo.findByExpressao(expressao.getExpressao())
                .ifPresentOrElse(
                        existingExpression -> {
                            // Se a expressão já existe, retorne um ExpressaoDTO com o resultado existente.
                            response = new ExpressaoDTO(existingExpression.getResult());
                        },
                        () -> {
                            // Se a expressão não existe, calcule o resultado e crie um novo Expressao.
                            ExpressaoDTO calculationResult = getCalculationResult(expressao);
                            expressao.setResult(calculationResult.getResultado());
                            calculadoraRepo.save(expressao);
                            response = calculationResult;
                        }
                );

        return response;
    }

    private ExpressaoDTO getCalculationResult(Expressao expressao) {
        ExpressaoDTO expressaoDTO = new ExpressaoDTO();

        DoubleEvaluator eval = new DoubleEvaluator();
        Double result = eval.evaluate(expressao.getExpressao());
        BigDecimal roundedResult = BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP);
        expressaoDTO.setResultado(roundedResult);

        return expressaoDTO;
    }
}
