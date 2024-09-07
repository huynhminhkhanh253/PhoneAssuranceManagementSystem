package com.api.ManagerService.model.commonData;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerInfo {

    private String buyerNumber;
    private String buyerName;
    private BuyerContact buyerContact;
    private BillingAddress billingAddress;
}
