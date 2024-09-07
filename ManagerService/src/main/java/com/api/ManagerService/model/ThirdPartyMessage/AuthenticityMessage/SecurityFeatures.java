package com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityFeatures {
    private String faceIdStatus;
    private String touchIdStatus;
    private String passcodeLockStatus;
}
