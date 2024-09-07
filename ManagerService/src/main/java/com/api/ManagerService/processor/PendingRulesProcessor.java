package com.api.ManagerService.processor;

import com.api.ManagerService.model.GuaranteeEvent;
import com.api.ManagerService.model.Order;
import com.api.ManagerService.model.commonData.Specs;
import com.api.ManagerService.service.GuaranteeEventService;
import com.api.ManagerService.service.OrderService;
import com.api.ManagerService.service.RulesServices.PendingRuleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PendingRulesProcessor extends ManagerServiceBaseProcessor {
    private String rawMessage;
    @Autowired
    private PendingRuleService pendingRuleService;
    @Autowired
    private GuaranteeEvent guaranteeEvent;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;
    @Autowired
    private Specs specs;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GuaranteeEventService guaranteeEventService;


    @Override
    public void prepareData(Exchange exchange) throws JsonProcessingException {
        // stored received data
        rawMessage = exchange.getIn().getBody().toString();
        guaranteeEvent = objectMapper.readValue(rawMessage, GuaranteeEvent.class);
        specs = guaranteeEvent.buildSpecs();
        order = guaranteeEvent.buildOrder();
        log.info("Received guarantee event: {}", guaranteeEvent);
    }
    @Override
    public void processData() throws Exception {
        // check rule and set order state
        String pendingResult = pendingRuleService.evaluateRules(order.getImei());
        if (pendingResult != null) {
            order.setPending_reason(pendingResult);
            order.setOrder_status("Pending");
        }
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
