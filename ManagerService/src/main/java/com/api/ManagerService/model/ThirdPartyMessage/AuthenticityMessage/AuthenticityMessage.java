package com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage;

import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticityMessage extends ThirdPartyMessage {
    private Authenticity authenticity;
}
