package com.api.ManagerService.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Scope("prototype")
public class Order {
    private long id;
    private String imei;
    private String phone_model;
    private String source;
    private String order_status;
    private String ineligible_reason;
    private String pending_reason;
    private double price;
    private String buyer_number;
    private String seller_number;
    private long specs_id;
}
