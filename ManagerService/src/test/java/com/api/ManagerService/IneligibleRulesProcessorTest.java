package com.api.ManagerService;

import com.api.ManagerService.mapper.GuaranteeEventMapper;
import com.api.ManagerService.mapper.OrderMapper;
import com.api.ManagerService.model.GuaranteeEventDatabaseAudit;
import com.api.ManagerService.model.Order;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@CamelSpringBootTest
public class IneligibleRulesProcessorTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GuaranteeEventMapper guaranteeEventMapper;

    @Test
    public void ineligibleRulesProcessorDatabaseEndpointTest () throws Exception {

        Order expectedOrder = new Order();
        expectedOrder.setIneligible_reason("Battery health is ineligible, Os version is ineligible");
        String input = Files.readString(Path.of("src/test/resources/testData/input.json"));
        String expectedIneligibleReason = Files.readString(Path.of("src/test/resources/testData/IneligibleReason.txt"));
        String inputImei = "70a25cd6-7c5d-4a75-afd2-2a180752e749";

        producerTemplate.sendBody("activemq:guarantee-event-queue", input);
        Thread.sleep(2000);
        Order receivedOrder = orderMapper.getOrderByImei(inputImei);

        GuaranteeEventDatabaseAudit receivedGuarantee = guaranteeEventMapper.getGuaranteeEventMessageByImei(inputImei);
        // mock database for testing

        // Order Check
        assertThat(receivedOrder).isNotNull();
        assertThat(receivedOrder.getIneligible_reason().equals(expectedIneligibleReason));

        // GuaranteeEvent Check
        assertThat(receivedGuarantee).isNotNull();
        assertThat(receivedGuarantee.getRawMessage().equals(input));
    }
}
