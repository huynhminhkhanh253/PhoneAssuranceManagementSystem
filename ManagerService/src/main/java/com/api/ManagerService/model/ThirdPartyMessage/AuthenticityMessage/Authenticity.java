package com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authenticity {
    private String imeiStatus;
    private String ownershipStatus;
    private SecurityFeatures securityFeatures;
    private WarrantyInformation warrantyInformation;
}
