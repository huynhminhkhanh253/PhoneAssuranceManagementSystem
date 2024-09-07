package com.api.ManagerService.service.RulesServices;

import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import com.api.ManagerService.service.ThirdPartyApiService;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FactsService {
    @Autowired
    private ThirdPartyApiService thirdPartyApiService;
    public Facts buildFacts(String imei){
        HashMap<String, ThirdPartyMessage> thirdPartyMessagesArray = new HashMap<>(thirdPartyApiService.getAllMessage(imei));
        Facts facts =  new Facts();
        for(Map.Entry<String, ThirdPartyMessage> entry : thirdPartyMessagesArray.entrySet()) {
            if(entry.getValue() != null) facts.put(entry.getKey(), entry.getValue());
        }
        return facts;
    }

}
