package com.api.ManagerService.model.commonData;

import com.api.ManagerService.model.GuaranteeEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommonMessage {
    private String imei;
    private String phoneModel;
    private String purchaseType;
    private String eventType;
    private BuyerInfo buyerInfo;
    private SellerInfo sellerInfo;
    private Specs specs;
    private TransactionDetails transactionDetails;

    public GuaranteeEvent convertToGuaranteeEvent(){
        GuaranteeEvent.GuaranteeEventBuilder builder = GuaranteeEvent.builder();
        builder.imei(getImei())
                .phoneModel(getPhoneModel())
                .purchaseType(getPurchaseType())
                .eventType(getEventType())
                .buyerNumber(getBuyerInfo().getBuyerContact().getPhone())
                .buyerName(getBuyerInfo().getBuyerName())
                .buyerEmail(getBuyerInfo().getBuyerContact().getEmail())
                .buyerPhone(getBuyerInfo().getBuyerContact().getPhone())
                .buyerStreet(getBuyerInfo().getBillingAddress().getStreet())
                .buyerCity(getBuyerInfo().getBillingAddress().getCity())
                .buyerState(getBuyerInfo().getBillingAddress().getState())
                .buyerZipCode(getBuyerInfo().getBillingAddress().getZipCode())
                .sellerNumber(getSellerInfo().getSellerContact().getPhone())
                .sellerName(getSellerInfo().getSellerName())
                .sellerEmail(getSellerInfo().getSellerContact().getEmail())
                .sellerPhone(getSellerInfo().getSellerContact().getPhone())
                .screenSize(getSpecs().getScreenSize())
                .ram(getSpecs().getRam())
                .cpu(getSpecs().getCpu())
                .storage(getSpecs().getStorage())
                .cameraMain(getSpecs().getCamera().getMain())
                .cameraFront(getSpecs().getCamera().getFront())
                .battery(getSpecs().getBattery())
                .transactionId(getTransactionDetails().getTransactionId())
                .purchaseDate(getTransactionDetails().getPurchaseDate())
                .status(getTransactionDetails().getStatus())
                .paymentMethod(getTransactionDetails().getPaymentMethod())
                .amount(getTransactionDetails().getAmount())
                .currency(getTransactionDetails().getCurrency());
        return builder.build();
    }
}
