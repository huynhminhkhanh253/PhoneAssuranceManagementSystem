package com.api.ManagerService.service.RulesServices;

import com.api.ManagerService.route.ConditionBean;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class BaseRuleService {
    @Autowired
    Rules rules;
    @Autowired
    private DefaultRulesEngine rulesEngine;
    @Autowired
    private FactsService factsService;
    @Autowired
    private ConditionBean conditionBean;

    public String evaluateRules(String imei) throws Exception {
        // put to facts of rule engine if not null
        String evaluateResults = new String();
        Facts facts = factsService.buildFacts(imei);
        //rules.register();
        Map<String, Object> ruleBeans = getRuleType();
        ruleBeans.values().forEach(rule -> rules.register(rule));


        for (Rule rule : rules) {
            if (rule.evaluate(facts)) {
                try {
                    rule.execute(facts);
                    String result = facts.get("result");
                    if (result != null && !result.isEmpty()) {
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        setBeanState();
        return null;


        //return evaluateResults;
    }
    public abstract Map<String, Object> getRuleType();
    public abstract void setBeanState();
}

