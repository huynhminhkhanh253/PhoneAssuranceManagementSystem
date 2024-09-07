package com.api.ManagerService.service;

import com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage.AuthenticityMessage;
import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.HistoryUsageMessage;
import com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage.PhysicalConditionMessage;
import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ThirdPartyApiService {
    @Autowired
    ThirdPartyApiProxy thirdPartyApiProxy;
    public HistoryUsageMessage getUsageHistoryByImei(String imei){
        return thirdPartyApiProxy.getHistoryUsageByImei(imei);
    }
    public PhysicalConditionMessage getPhysicalConditionByImei(String imei){
        return thirdPartyApiProxy.getPhysicalConditionByImei(imei);
    }
    public AuthenticityMessage getAuthenticityPhoneByImei(String imei){
        return thirdPartyApiProxy.getAuthenticityPhoneByImei(imei);
    }

    public HashMap<String, ThirdPartyMessage> getAllMessage(String imei){
        HashMap<String, ThirdPartyMessage> results = new HashMap<>();
        results.put("HistoryUsageMessage", getUsageHistoryByImei(imei));
        results.put("PhysicalConditionMessage", getPhysicalConditionByImei(imei));
        results.put("AuthenticityPhoneMessage", getAuthenticityPhoneByImei(imei));
        return results;
    }

}
