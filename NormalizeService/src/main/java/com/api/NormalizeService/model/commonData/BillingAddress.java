package com.api.NormalizeService.model.commonData;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingAddress {
    private String street;
    private String city;
    private String state;
    private long zipCode;
}
