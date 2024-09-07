package com.api.ManagerService.model.commonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetails  {
    private String transactionId;
    private String purchaseDate;
    private String status;
    private String paymentMethod;
    private double amount;
    private String currency;
}
