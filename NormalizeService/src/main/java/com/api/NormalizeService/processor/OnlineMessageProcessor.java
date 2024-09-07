package com.api.NormalizeService.processor;

import com.api.NormalizeService.model.OnlineMessage.OnlineMessage;
import com.api.NormalizeService.model.commonData.CommonMessage;
import com.api.NormalizeService.service.MessageServices;
import com.api.NormalizeService.service.OnlineMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnlineMessageProcessor extends BaseProcessor {
    @Autowired
    private OnlineMessageService onlineMessageService;
    @Override
    protected CommonMessage getMessage(Exchange exchange) throws JsonProcessingException {
        return objectMapper.readValue(exchange.getIn().getBody().toString(),OnlineMessage.class);
    }
    @Override
    protected MessageServices getMessageServices() {
        return onlineMessageService;
    }
}
