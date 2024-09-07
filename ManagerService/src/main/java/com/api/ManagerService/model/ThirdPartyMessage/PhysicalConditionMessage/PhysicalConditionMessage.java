package com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage;

import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalConditionMessage extends ThirdPartyMessage {
    private PhysicalCondition physicalCondition;
}
