//package com.example.calculadorarest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//
//import com.example.calculadorarest.config.SecurityConfig;
//import com.example.calculadorarest.controller.CalculadoraController;
//import com.example.calculadorarest.dto.ExpressaoDTO;
//import com.example.calculadorarest.entity.Expressao;
//import com.example.calculadorarest.repository.CalculadoraRepo;
//import com.example.calculadorarest.service.CalculadoraService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
////@SpringBootTest
//@SpringBootTest(classes = CalculadoraRestApplication.class)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@Import(SecurityConfig.class)
//public class CalculadoraControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CalculadoraService calculadoraService;
//
//    @MockBean
//    private AuthenticationManager authenticationManager; // Mock AuthenticationManager
//
//    @BeforeEach
//    void setUp() {
//        when(authenticationManager.authenticate(any(Authentication.class)))
//                .thenReturn(null);
//
//        SecurityContextHolder.clearContext();
//    }
//
//    @Nested
//    @Test
//    @DisplayName("Cenário 1 - Dada a expressao (2+2)*3, retonar o valor 12 ")
//    void cenario1() throws Exception {
//        // Mock do resultado retornado pelo serviço
//        ExpressaoDTO expressaoDTO = new ExpressaoDTO();
//        expressaoDTO.setResultado(new BigDecimal("12.00")); // O resultado esperado
//
//        // Configurar o comportamento do serviço mock
//        when(calculadoraService.saveExpression(any(Expressao.class))).thenReturn(expressaoDTO);
//
//        // Simular uma chamada à API
//        Expressao expressao = new Expressao();
//        expressao.setExpressao("(2+2)*3");
//
//        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(expressao)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value("12.00"))
//                .andReturn();
//
//        String jsonResponse = response.getResponse().getContentAsString();
//        ObjectNode responseNode = objectMapper.readValue(jsonResponse, ObjectNode.class);
//        String resultado = responseNode.get("resultado").textValue();
//
//        // Verificar se o serviço foi chamado com a expressão correta
//        Mockito.verify(calculadoraService, times(1)).saveExpression(expressao);
//
//        // Verificar se o resultado da API corresponde ao valor esperado
//        assertEquals(new BigDecimal("12.00"), resultado);
//    }
//
////    @Test
////    @DisplayName("Cenário 2 - Dada a expressao (2+2)*3, retonar o valor 12 ")
////     void testCenario2() {
////         // Configurar o mock para simular a busca por uma expressão existente no banco
////         String expressao = "2.2+2.2";
////         Expressao expressaoExistente = new Expressao();
////         expressaoExistente.setExpressao(expressao);
////         expressaoExistente.setResult(new BigDecimal("4.40")); // Suponhamos que o resultado exista no banco
////         when(calculadoraRepo.findByExpressao(expressao)).thenReturn(Optional.of(expressaoExistente));
////
////         // Primeira chamada: Deve retornar o resultado do banco
////         ExpressaoDTO resultado1 = calculadoraService.saveExpression(new Expressao(expressao));
////         assertEquals("4.40", resultado1.getResultado());
////
////         // Segunda chamada com a mesma expressão: Deve retornar o mesmo resultado do banco
////         ExpressaoDTO resultado2 = calculadoraService.saveExpression(new Expressao(expressao));
////         assertEquals("4.40", resultado2.getResultado());
////
////         // Verifique se o método findByExpressao foi chamado apenas uma vez no primeiro salvamento
////         verify(calculadoraRepo, times(1)).findByExpressao(expressao);
////
////         // Verifique se o método findByExpressao foi chamado uma vez adicional na segunda chamada
////         verify(calculadoraRepo, times(2)).findByExpressao(expressao);
////
////         // Certifique-se de que o método save nunca foi chamado, já que não precisamos salvá-lo novamente
////         verify(calculadoraRepo, never()).save(any());
////    }
//}
