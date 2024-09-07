package com.api.ManagerService.processor;

import com.api.ManagerService.model.GuaranteeEvent;
import com.api.ManagerService.model.Order;
import com.api.ManagerService.model.commonData.Specs;
import com.api.ManagerService.service.GuaranteeEventService;
import com.api.ManagerService.service.OrderService;
import com.api.ManagerService.service.RulesServices.IneligibleRuleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class IneligibleRulesProcessor extends ManagerServiceBaseProcessor{
    @Autowired
    private IneligibleRuleService ineligibleRuleService;
    @Autowired
    private Order order;
    @Autowired
    private Specs specs;
    @Autowired
    private GuaranteeEvent guaranteeEvent;
    @Autowired
    OrderService orderService;
    @Autowired
    ObjectMapper objectMapper;
    private String rawMessage;
    @Autowired
    private GuaranteeEventService guaranteeEventService;
    @Override
    public void prepareData(Exchange exchange) throws JsonProcessingException {
        //store data
        rawMessage = exchange.getIn().getBody().toString();
        guaranteeEvent = objectMapper.readValue(rawMessage, GuaranteeEvent.class);
        specs = guaranteeEvent.buildSpecs();
        order = guaranteeEvent.buildOrder();
        log.info("Received guarantee event: {}", guaranteeEvent);
    }

    @Override
    public void processData() throws Exception {
        // check rule and set order state
        String ineligibleResult = ineligibleRuleService.evaluateRules(order.getImei());
        if (ineligibleResult != null) {
            order.setIneligible_reason(ineligibleResult);
            order.setOrder_status("Ineligible");
        }
        // else, do nothing
    }

    @Override
    public void postProcess() {
        // add guaranteeEvent to db to audit
        guaranteeEventService.addGuaranteeEventToDb(guaranteeEvent, rawMessage);
        // add or update order to db
        orderService.addOrUpdateOrder(order, specs);
        log.info("Saved order to database: {}", order);
    }
}
