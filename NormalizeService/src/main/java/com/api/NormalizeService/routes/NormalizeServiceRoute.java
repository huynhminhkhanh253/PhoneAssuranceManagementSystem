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
        from("activemq:online-queue?concurrentConsumers=10&maxConcurrentConsumers=30")
                .routeId("online-queue")
                .threads()
                    .poolSize(10)
                    .maxPoolSize(30)
                    .maxQueueSize(1000)
                .process(onlineMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
        from("activemq:physical-queue")
                .routeId("physical-queue")
                .threads()
                    .poolSize(10)
                    .maxPoolSize(30)
                    .maxQueueSize(1000)
                .process(physicalMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
        from("activemq:partner-queue")
                .routeId("partner-queue")
                .threads()
                    .poolSize(10)
                    .maxPoolSize(30)
                    .maxQueueSize(1000)
                .process(partnerMessageProcessor)
                .log("${body}")
                .to("activemq:queue:guarantee-event-queue");
    }
}
