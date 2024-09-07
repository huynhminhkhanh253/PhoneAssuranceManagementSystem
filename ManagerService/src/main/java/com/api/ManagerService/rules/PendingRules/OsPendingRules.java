package com.api.ManagerService.rules.PendingRules;

import com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage.AuthenticityMessage;
import com.api.ManagerService.rules.ManagerServiceRules;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "Os Pending Rule")
@PendingRule
@Component
@Scope("prototype")
public class OsPendingRules extends ManagerServiceRules {
    @Override
    @Condition
    public boolean when(Facts facts) {
        AuthenticityMessage authenticityMessage = facts.get("AuthenticityPhoneMessage");
        if(authenticityMessage != null){
            return authenticityMessage.getAuthenticity().getImeiStatus().equals("Pending");
        }
        return false;
    }
    @Override
    @Action
    public void then(Facts facts){
        facts.put("result", "Os version is pending update");
    }
}
