package com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistory {
    private String timeStamp;
    private String serviceType;
    private String details;
}
