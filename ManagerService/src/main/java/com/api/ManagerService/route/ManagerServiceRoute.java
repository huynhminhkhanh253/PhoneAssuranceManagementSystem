package com.api.ManagerService.route;

import com.api.ManagerService.processor.IneligibleRulesProcessor;
import com.api.ManagerService.processor.PendingRulesProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerServiceRoute extends RouteBuilder {
    @Autowired
    IneligibleRulesProcessor ineligibleRulesProcessor;
    @Autowired
    PendingRulesProcessor pendingRulesProcessor;
    @Autowired
    ConditionBean conditionBean;

    @Override
    public void configure() throws Exception {
        // Concurrency processing
        from("activemq:guarantee-event-queue")
                //.multicast()
                //.synchronous()
                .to("direct:ineligibleProcessor")
                    .choice()
                        .when().method(conditionBean, "evaluateCondition")
                            .to("direct:pendingProcessor")
                        .otherwise()
                        //more processor ...
                .end();

        from("direct:ineligibleProcessor")
                .process(ineligibleRulesProcessor)
                //.to("${body.nextRoute}")
                .log("${body}");

        from("direct:pendingProcessor")
                .process(pendingRulesProcessor)
                // reset bean state
                .bean(conditionBean, "setState(false)")
                .log("${body}");
    }
}
