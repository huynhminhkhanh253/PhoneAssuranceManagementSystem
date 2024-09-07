package com.api.NormalizeService;

import com.api.NormalizeService.mapper.OnlineMessageMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


@CamelSpringBootTest
@SpringBootTest()
@MockEndpoints("activemq:queue:guarantee-event-queue")
public class NormalizeServiceRouteEndpointTest {
    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject("mock:activemq:queue:guarantee-event-queue")
    private MockEndpoint mockEndpoint;

    @MockBean
    private OnlineMessageMapper onlineMessageMapper;


    @Test
    public void onlineRouteEndpointTest() throws Exception {
        // Prepare data
        String input = Files.readString(Path.of("src/test/resources/testData/onlineRoute/onlineQueueInput.json"));
        String output = Files.readString(Path.of("src/test/resources/testData/onlineRoute/onlineQueueOutput.json"));
        // Mok endpoint with expected output
        mockEndpoint.expectedBodiesReceived(output);
        producerTemplate.sendBody("activemq:queue:online-queue", input);
        // Validate
        mockEndpoint.assertIsSatisfied();
    }
//    @Test
//    public void onlineRouteWithMockDbEndpointTest() throws Exception {
//        String input = Files.readString(Path.of("src/test/resources/testData/onlineRoute/onlineQueueInput.json"));
//        String output = Files.readString(Path.of("src/test/resources/testData/onlineRoute/onlineQueueOutput.json"));
//        //doNothing().when(onlineMessageMapper).save(any(), any(), any());
//        mockEndpoint.expectedBodiesReceived(output);
//        producerTemplate.sendBody("activemq:queue:online-queue", input);
//        mockEndpoint.assertIsSatisfied();
//        verify(onlineMessageMapper, times(1)).save(any(), any(), any());
//    }
    @Test
    public void physicalRouteEndpointTest() throws Exception {
        // expectations
        String input = Files.readString(Path.of("src/test/resources/testData/physicalRoute/physicalQueueInput.json"), StandardCharsets.UTF_8);
        String output = Files.readString(Path.of("src/test/resources/testData/physicalRoute/physicalQueueOutput.json"), StandardCharsets.UTF_8);
        mockEndpoint.expectedBodiesReceived(output);
        producerTemplate.sendBody("activemq:queue:physical-queue", input);
        mockEndpoint.assertIsSatisfied();
    }
    @Test
    public void partnerRouteEndpointTest() throws Exception {
        // expectations
        String input = Files.readString(Path.of("src/test/resources/testData/partnerRoute/partnerQueueInput.xml"), StandardCharsets.UTF_8);
        String output = Files.readString(Path.of("src/test/resources/testData/partnerRoute/partnerQueueOutput.json"), StandardCharsets.UTF_8);
        mockEndpoint.expectedBodiesReceived(output);
        producerTemplate.sendBody("activemq:queue:partner-queue", input);
        mockEndpoint.assertIsSatisfied();
    }
}
