package com.api.NormalizeService.processor;

import com.api.NormalizeService.model.PartnerMessage.PartnerMessage;
import com.api.NormalizeService.model.commonData.CommonMessage;
import com.api.NormalizeService.service.MessageServices;
import com.api.NormalizeService.service.PartnerMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringReader;

@Component
@Slf4j
@RequiredArgsConstructor
public class PartnerMessageProcessor extends BaseProcessor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PartnerMessageService partnerMessageService;


    @Override
    protected CommonMessage getMessage(Exchange exchange) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PartnerMessage.class);
        return (PartnerMessage) context.createUnmarshaller().unmarshal(new StringReader(exchange.getIn().getBody().toString()));
    }

    @Override
    protected MessageServices getMessageServices() {
        return partnerMessageService;
    }
}
