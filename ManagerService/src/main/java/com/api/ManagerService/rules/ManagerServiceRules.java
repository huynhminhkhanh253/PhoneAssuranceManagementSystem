package com.api.ManagerService.rules;

import org.jeasy.rules.api.Facts;

public abstract class ManagerServiceRules {

    public abstract boolean when(Facts facts);
    public abstract void then(Facts facts);

}
