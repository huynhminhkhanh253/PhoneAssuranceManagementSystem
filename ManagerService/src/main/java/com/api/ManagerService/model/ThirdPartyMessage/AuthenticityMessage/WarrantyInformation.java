package com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarrantyInformation {
    private String warrantyStatus;
    private String warrantyExpiration;
    private ArrayList<ServiceHistory> serviceHistory;
}
