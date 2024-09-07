package com.api.ManagerService.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public abstract class ManagerServiceBaseProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            prepareData(exchange);
            processData();
            postProcess();
        }
        catch(Exception e){
            log.error("", e);
        }
    }
    public abstract void prepareData(Exchange exchange) throws Exception;
    public abstract void processData() throws Exception;
    public abstract void postProcess();
}
