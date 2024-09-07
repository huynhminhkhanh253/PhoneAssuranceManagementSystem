package com.api.ManagerService.service.RulesServices;

import com.api.ManagerService.route.ConditionBean;
import com.api.ManagerService.rules.IneligibleRules.IneligibleRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IneligibleRuleService extends BaseRuleService {
    @Autowired
    ApplicationContext context;
    @Autowired
    ConditionBean conditionBean;
    @Override
    public Map<String, Object> getRuleType() {
        return context.getBeansWithAnnotation(IneligibleRule.class);
    }

    @Override
    public void setBeanState() {
        conditionBean.setState(true);
    }

}
