package com.api.ManagerService;

import com.api.ManagerService.mapper.GuaranteeEventMapper;
import com.api.ManagerService.mapper.OrderMapper;
import com.api.ManagerService.model.GuaranteeEventDatabaseAudit;
import com.api.ManagerService.model.Order;
import com.api.ManagerService.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@CamelSpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ManagerServiceDatabaseEndpointTest  {
    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    GuaranteeEventMapper guaranteeEventMapper;

    @Test
    public void orderDatabaseEndpointTest() throws Exception {
        // prepare input
        String input = Files.readString(Path.of("src/test/resources/testData/input.json"));
        String input_imei = "70a25cd6-7c5d-4a75-afd2-2a180752e749";
        String ineligibleReason = "src/test/resources/testData/IneligibleReason.txt";
        // prepare expected output
        Order expectedOrder = new Order();
        expectedOrder.setPhone_model("iPhone 4");
        expectedOrder.setSource("Online");
        expectedOrder.setIneligible_reason(ineligibleReason);
        // send message
        producerTemplate.sendBody("activemq:queue:guarantee-event-queue", input);
        Thread.sleep(2000);
        // access database to get order
        Order receivedOrder = orderMapper.getOrderByImei(input_imei);
        // Compare expected and input data
        assertThat(receivedOrder).isNotNull();
        assertThat(receivedOrder.getPhone_model().equals(expectedOrder.getPhone_model()));
        assertThat(receivedOrder.getSource().equals(expectedOrder.getSource()));
        assertThat(receivedOrder.getIneligible_reason().equals(expectedOrder.getIneligible_reason()));
    }

    @Test
    public void guaranteeEventDatabaseEndpointTest() throws Exception {
        String input = Files.readString(Path.of("src/test/resources/testData/input.json"));
        String input_imei = "70a25cd6-7c5d-4a75-afd2-2a180752e749";
        producerTemplate.sendBody("activemq:queue:guarantee-event-queue", input);
        Thread.sleep(2000);
        GuaranteeEventDatabaseAudit receivedGuarantee = guaranteeEventMapper.getGuaranteeEventMessageByImei(input_imei);
        // Guarantee
        assertThat(receivedGuarantee).isNotNull();
        assertThat(receivedGuarantee.getRawMessage().equals(input));
    }
}
