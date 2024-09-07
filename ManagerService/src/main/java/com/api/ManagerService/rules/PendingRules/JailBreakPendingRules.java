package com.api.ManagerService.rules.PendingRules;

import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.HistoryUsageMessage;
import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.UsageHistory;
import com.api.ManagerService.rules.ManagerServiceRules;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "Jail Break Pending Rule")
@Component
@PendingRule
@Scope("prototype")
public class JailBreakPendingRules extends ManagerServiceRules {
    @Override
    @Condition
    public boolean when(Facts fact) {
        HistoryUsageMessage historyUsageMessage = fact.get("HistoryUsageMessage");
        if(historyUsageMessage != null){
            for(UsageHistory history : historyUsageMessage.getUsageHistories()){
                if(history.getEvent().equals("Jail Break") && history.getDetails().equals("Pending investigate")){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    @Action
    public void then(Facts facts) {
        facts.put("result", "Jail Break Status pending investigate");
    }
}
