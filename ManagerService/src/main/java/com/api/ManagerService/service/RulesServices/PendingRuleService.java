package com.api.ManagerService.service.RulesServices;


import com.api.ManagerService.route.ConditionBean;
import com.api.ManagerService.rules.PendingRules.PendingRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PendingRuleService extends BaseRuleService{
    @Autowired
    ApplicationContext context;
    @Autowired
    ConditionBean conditionBean;
    @Override
    public Map<String, Object> getRuleType() {
        return context.getBeansWithAnnotation(PendingRule.class);
    }

    @Override
    public void setBeanState() {

    }

}
