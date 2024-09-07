package com.api.NormalizeService.processor;

import com.api.NormalizeService.model.PhysicalMessage.PhysicalMessage;
import com.api.NormalizeService.model.commonData.CommonMessage;
import com.api.NormalizeService.service.MessageServices;
import com.api.NormalizeService.service.PhysicalMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhysicalMessageProcessor extends BaseProcessor{
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PhysicalMessageService physicalMessageService;

    @Override
    protected CommonMessage getMessage(Exchange exchange) throws JsonProcessingException {
        return objectMapper.readValue(exchange.getIn().getBody().toString(), PhysicalMessage.class);
    }

    @Override
    protected MessageServices getMessageServices() {
        return physicalMessageService;
    }
}
