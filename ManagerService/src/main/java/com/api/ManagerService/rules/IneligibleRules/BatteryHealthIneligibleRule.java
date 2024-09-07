package com.api.ManagerService.rules.IneligibleRules;

import com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage.PhysicalConditionMessage;
import com.api.ManagerService.rules.ManagerServiceRules;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "Battery Health Rule")
@Component
@IneligibleRule
@Scope("prototype")
public class BatteryHealthIneligibleRule extends ManagerServiceRules {
    @Override
    @Condition
    public boolean when(Facts facts) {
        PhysicalConditionMessage physicalConditionMessage = facts.get("PhysicalConditionMessage");
        if(physicalConditionMessage != null){
            return physicalConditionMessage.getPhysicalCondition().getBatteryHealthNumber() < 80;
        }
        return false;
    }

    @Override
    @Action
    public void then(Facts facts) {
        facts.put("result", "Battery health is ineligible");
    }
}
