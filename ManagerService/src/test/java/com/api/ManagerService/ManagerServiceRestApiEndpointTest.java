package com.api.ManagerService;

import com.api.ManagerService.mapper.OrderMapper;
import com.api.ManagerService.model.User;
import com.api.ManagerService.service.JwtService;
import com.api.ManagerService.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ManagerServiceRestApiEndpointTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void getAllOrderApiEndpointTest() throws Exception {
        String token = jwtService.generateToken("HuynhMinhKhanh");
        mockMvc.perform(MockMvcRequestBuilders.get("/managerService/order/getAllOrder")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getOrderByImeiIntegrationTest() throws Exception{
        //prepare input
        String input = Files.readString(Path.of("src/test/resources/testData/input.json"));
        String input_imei = "70a25cd6-7c5d-4a75-afd2-2a180752e749";
        producerTemplate.sendBody("activemq:queue:guarantee-event-queue", input);
        Thread.sleep(2000);
        String token = jwtService.generateToken("HuynhMinhKhanh");
        //perform simulation of sending request
        mockMvc.perform(MockMvcRequestBuilders.get("/managerService/order/getOrderByImei/{imei}", input_imei)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                // Compare results
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.imei").value(input_imei));
    }
    @Test
    public void userRegisterAndLoginApiEndpointTest() throws Exception {
        // expect
        User user = new User();
        user.setUserName("HuynhKhanh");
        user.setPassword("123456");
        mockMvc.perform(post("/managerService/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\" : \"HuynhKhanh\",\n" +
                        "    \"password\" : \"123456\"\n" +
                        "}"))
                .andExpect(status().isOk());

        User savedUser = userService.findUser("HuynhKhanh");
        // verify register
        mockMvc.perform(get("/managerService/user/find/" + savedUser.getUserName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.userName", is(user.getUserName())))
                .andExpect(result -> assertTrue(bCryptPasswordEncoder.matches(user.getPassword(), savedUser.getPassword() )));
        mockMvc.perform(post("/managerService/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\" : \"HuynhKhanh\",\n" +
                        "    \"password\" : \"123456\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}
