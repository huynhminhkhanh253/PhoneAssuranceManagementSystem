package com.api.NormalizeService;

import com.api.NormalizeService.mapper.OnlineMessageMapper;
import com.api.NormalizeService.mapper.PartnerMessageMapper;
import com.api.NormalizeService.mapper.PhysicalMessageMapper;
import com.api.NormalizeService.model.OnlineMessage.OnlineMessageAudit;
import com.api.NormalizeService.model.PartnerMessage.PartnerMessageAudit;
import com.api.NormalizeService.model.PhysicalMessage.PhysicalMessageAudit;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


@CamelSpringBootTest
@SpringBootTest()
@MockEndpoints("activemq:queue:guarantee-event-queue")
public class NormalizeServiceDatabaseEndpointTest {
    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private OnlineMessageMapper onlineMessageMapper;
    @Autowired
    private PhysicalMessageMapper physicalMessageMapper;
    @Autowired
    private PartnerMessageMapper partnerMessageMapper;


    @Test
    public void onlineRouteDatabaseEndpointTest() throws Exception {
        // prepare input data
        String input_imei = "70a25cd6-7c5d-4a75-afd2-2a180752e749";
        String input = Files.readString(Path.of("src/test/resources/testData/onlineRoute/onlineQueueInput.json"));
        // prepare expected output database data
        OnlineMessageAudit onlineMessageAudit = new OnlineMessageAudit();
        onlineMessageAudit.setRawMessage(input);
        onlineMessageAudit.setImei(input_imei);
        // send input
        producerTemplate.sendBody("activemq:queue:online-queue", input);
        Thread.sleep(2000); // wait camel to process message
        OnlineMessageAudit received = onlineMessageMapper.getMessageByImei(input_imei);
        // access database to check result
        assertThat(received).isNotNull();
        assertThat((received.getRawMessage()).equals(onlineMessageAudit.getRawMessage()));
        assertThat(received.getImei().equals(onlineMessageAudit.getImei()));
    }
    @Test
    public void physicalRouteDatabaseEndpointTest() throws Exception {
        String input_imei = "30a25cd6-7c5d-4a75-afd2-2a180752e743";

        String input = Files.readString(Path.of("src/test/resources/testData/physicalRoute/physicalQueueInput.json"));
        // expect
        PhysicalMessageAudit physicalMessageAudit = new PhysicalMessageAudit();
        physicalMessageAudit.setRawMessage(input);
        physicalMessageAudit.setImei(input_imei);

        producerTemplate.sendBody("activemq:queue:physical-queue", input);
        Thread.sleep(2000); // wait camel to process message
        PhysicalMessageAudit received = physicalMessageMapper.getMessageByImei(input_imei);
        assertThat(received).isNotNull();
        assertThat((received.getRawMessage()).equals(physicalMessageAudit.getRawMessage()));
        assertThat(received.getImei().equals(physicalMessageAudit.getImei()));
    }
    @Test
    public void partnerRouteDatabaseEndpointTest() throws Exception {
        String input_imei = "70a25cd6-7c5d-4a75-afd2-2a180752e748";

        String input = Files.readString(Path.of("src/test/resources/testData/partnerRoute/partnerQueueInput.xml"));
        // expect
        PartnerMessageAudit partnerMessageAudit = new PartnerMessageAudit();
        partnerMessageAudit.setImei(input_imei);


        producerTemplate.sendBody("activemq:queue:partner-queue", input);
        Thread.sleep(2000); // wait camel to process message
        PartnerMessageAudit received = partnerMessageMapper.getMessageByImei(input_imei);
        assertThat(received).isNotNull();
        assertThat((received.getRawMessage()).equals(partnerMessageAudit.getRawMessage()));
        assertThat(received.getImei().equals(partnerMessageAudit.getImei()));
    }
}
