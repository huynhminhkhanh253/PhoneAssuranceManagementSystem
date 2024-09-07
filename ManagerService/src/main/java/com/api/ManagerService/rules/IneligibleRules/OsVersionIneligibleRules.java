package com.api.ManagerService.rules.IneligibleRules;

import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.HistoryUsageMessage;
import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.UsageHistory;
import com.api.ManagerService.rules.ManagerServiceRules;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "Os version Rule")
@IneligibleRule
@Component
@Scope("prototype")
public class OsVersionIneligibleRules extends ManagerServiceRules {
    @Override
    @Condition
    public boolean when(Facts facts) {
        HistoryUsageMessage historyUsageMessage = facts.get("HistoryUsageMessage");
        if(historyUsageMessage != null){
            for(UsageHistory history : historyUsageMessage.getUsageHistories()){
                if(history.getEvent().equals("Operating System Update") && history.getOsVersion() < 10){
                    return true;
                }
            }
        }

        return false;
    }
    @Override
    @Action
    public void then(Facts facts){
        facts.put("result","Os version is ineligible");
    }
}
