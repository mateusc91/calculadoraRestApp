//package com.example.calculadorarest;
//
//import com.example.calculadorarest.config.SecurityConfig;
//import com.example.calculadorarest.controller.AuthController;
//import com.example.calculadorarest.entity.Role;
//import com.example.calculadorarest.entity.User;
//import com.example.calculadorarest.payload.request.LoginRequest;
//import com.example.calculadorarest.payload.request.SignupRequest;
//import com.example.calculadorarest.payload.response.JwtResponse;
//import com.example.calculadorarest.repository.RoleRepo;
//import com.example.calculadorarest.repository.UserRepo;
//import com.example.calculadorarest.security.jwt.JwtUtils;
//import com.example.calculadorarest.utils.ERole;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(classes = CalculadoraRestApplication.class)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@Import(SecurityConfig.class)
//public class AuthControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthenticationManager authenticationManager; // Mock AuthenticationManager
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private UserRepo userRepository;
//
//    @BeforeEach
//    public void setUp() {
//        // Configure the AuthenticationManager to return a dummy authentication
//        when(authenticationManager.authenticate(any(Authentication.class)))
//                .thenReturn(null);
//
//        SecurityContextHolder.clearContext();
//    }
//
//    @Test
//    @WithMockUser(username = "testuser1", password = "testpassword1", roles = "ADMIN")
//    public void testAuthController() throws Exception {
//        Set<String> roles = new HashSet<>();
//        roles.add("admin");
//        SignupRequest signupRequest = new SignupRequest("testuser1",roles, "testpassword1");
//
//        MvcResult loginResult = mockMvc.perform(post("/auth/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(signupRequest))
//                        .with(csrf())) // Add CSRF token
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        Optional<User> usuario = userRepository.findByUsername("testuser1");
//        assertThat(usuario).isPresent();
//
//        LoginRequest loginRequest = new LoginRequest("testuser1","testpassword1");
//
//        mockMvc.perform(post("/auth/signin")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest))
//                        .with(csrf())) // Add CSRF token
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        String token = "your-generated-jwt-token"; // You need to extract the token from the response here
//
//        String expressao = "(2+2)*3"; // Your expression here
//
//        mockMvc.perform(post("/api/calculate")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(expressao)
//                        .with(csrf())) // Add CSRF token
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//}