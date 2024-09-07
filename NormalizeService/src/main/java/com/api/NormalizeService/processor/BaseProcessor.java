package com.api.NormalizeService.processor;

import com.api.NormalizeService.model.GuaranteeEvent;
import com.api.NormalizeService.model.commonData.CommonMessage;
import com.api.NormalizeService.service.MessageServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@RequiredArgsConstructor
public abstract class BaseProcessor implements Processor {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            CommonMessage commonMessage = getMessage(exchange);
            MessageServices messageServices = getMessageServices();

            messageServices.addToDb(exchange.getIn().getBody().toString(), commonMessage.getImei(), commonMessage.getSpecsId());
            log.info("received {} message: {}",commonMessage.getClass(), commonMessage);
            GuaranteeEvent guaranteeEvent = commonMessage.convertToGuaranteeEvent();
            log.info("Converted: {}", guaranteeEvent);
            exchange.getIn().setBody(objectMapper.writeValueAsString(guaranteeEvent));
        } catch (Exception e) {
            log.error("",e);
        }
    }

    protected abstract CommonMessage getMessage(Exchange exchange) throws JsonProcessingException, JAXBException;
    protected abstract MessageServices getMessageServices();
}
