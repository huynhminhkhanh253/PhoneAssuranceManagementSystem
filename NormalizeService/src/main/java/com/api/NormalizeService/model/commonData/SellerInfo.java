package com.api.NormalizeService.model.commonData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerInfo {
    private String sellerNumber;
    private String sellerName;
    @OneToOne(cascade = CascadeType.ALL)
    private SellerContact sellerContact;
    @OneToOne(cascade = CascadeType.ALL)
    private StoreLocation storeLocation;
}
