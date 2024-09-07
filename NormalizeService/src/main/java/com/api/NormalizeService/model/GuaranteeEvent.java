package com.api.NormalizeService.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder(toBuilder = true)
public class GuaranteeEvent {
    private String imei;
    private String phoneModel;
    private String purchaseType;
    private String eventType;
    private String buyerNumber;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String buyerStreet;
    private String buyerCity;
    private String buyerState;
    private long buyerZipCode;
    private String sellerNumber;
    private String sellerName;
    private String sellerEmail;
    private String sellerPhone;
    private double online_price;
    private double physical_price;
    private UUID historyID;
    private String Channel;
    private String storeName;
    private String storeId;
    private String partnerName;
    private String partnerId;
    private String partnerCity;
    private String partnerState;
    private long partnerZipcode;
    private String exclusiveOffer;
    private double screenSize;
    private String ram;
    private String cpu;
    private String storage;
    private String cameraMain;
    private String cameraFront;
    private String battery;
    private String transactionId;
    private String purchaseDate;
    private String status;
    private String paymentMethod;
    private double amount;
    private String currency;
    private String inStoreExperience;
    private String voucherCode;
    private UUID voucherId;
    private int discountPercentage;
    private String validUntil;
}
