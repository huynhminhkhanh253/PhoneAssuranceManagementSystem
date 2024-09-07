package com.api.NormalizeService.routes;

import com.api.NormalizeService.processor.OnlineMessageProcessor;
import com.api.NormalizeService.processor.PartnerMessageProcessor;
import com.api.NormalizeService.processor.PhysicalMessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalizeServiceRoute extends RouteBuilder {
    @Autowired
    OnlineMessageProcessor onlineMessageProcessor;
    @Autowired
    PartnerMessageProcessor partnerMessageProcessor;
    @Autowired
    PhysicalMessageProcessor physicalMessageProcessor;
    @Override
    public void configure() throws Exception {
        //queue input
        //transformation
        //database output
        from("activemq:online-queue")
                .routeId("online-queue")
                .process(onlineMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
        from("activemq:physical-queue")
                .routeId("physical-queue")
                .process(physicalMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
        from("activemq:partner-queue")
                .routeId("partner-queue")
                .process(partnerMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
    }
}
