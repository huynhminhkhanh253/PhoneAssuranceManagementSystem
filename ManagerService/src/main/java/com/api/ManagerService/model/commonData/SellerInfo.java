package com.api.ManagerService.model.commonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerInfo {
    private String sellerName;
    private SellerContact sellerContact;
    private StoreLocation storeLocation;
}
