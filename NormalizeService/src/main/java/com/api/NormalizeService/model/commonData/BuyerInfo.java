package com.api.NormalizeService.model.commonData;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BuyerInfo {

    private long buyerNumber;
    private String buyerName;
    @OneToOne(cascade = CascadeType.ALL)
    private BuyerContact buyerContact;
    private BillingAddress billingAddress;
}
