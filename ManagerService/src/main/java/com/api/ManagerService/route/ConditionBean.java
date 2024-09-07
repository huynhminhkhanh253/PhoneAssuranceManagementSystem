package com.api.ManagerService.route;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ConditionBean {
    private boolean state;
    public boolean evaluateCondition() {
        return state;
    }
}
