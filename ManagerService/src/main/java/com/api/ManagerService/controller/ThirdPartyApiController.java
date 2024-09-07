package com.api.ManagerService.controller;

import com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage.AuthenticityMessage;
import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.HistoryUsageMessage;
import com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage.PhysicalConditionMessage;
import com.api.ManagerService.service.DroolsService;
import com.api.ManagerService.service.ThirdPartyApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ThirdPartyApiController {
    @Autowired
    ThirdPartyApiService thirdPartyApiService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DroolsService droolsService;
    @GetMapping("/history-usage/{imei}")
    public HistoryUsageMessage getHistoryUsageByImei(@PathVariable("imei") String imei){
        return thirdPartyApiService.getUsageHistoryByImei(imei);
    }
    @GetMapping("/physical-condition/{imei}")
    public PhysicalConditionMessage getPhysicalConditionByImei(@PathVariable("imei") String imei){
        return thirdPartyApiService.getPhysicalConditionByImei(imei);
    }
    @GetMapping("/historyUsageCheckByImei/{imei}")
    public ArrayList<String> checkUsageHistory(@PathVariable("imei") String imei){
        HistoryUsageMessage historyUsageMessage = thirdPartyApiService.getUsageHistoryByImei(imei);
        return droolsService.executeRule(historyUsageMessage).get("ineligibleResults");
    }

    @GetMapping("/physicalConditionCheckByImei/{imei}")
    public ArrayList<String> checkPhysicalCondition(@PathVariable("imei") String imei){
        PhysicalConditionMessage physicalConditionMessage = thirdPartyApiService.getPhysicalConditionByImei(imei);
        return droolsService.executeRule(physicalConditionMessage).get("ineligibleResults");
    }

    @GetMapping("/authenticity-phone/{imei}")
    public AuthenticityMessage getAuthenticityByImei(@PathVariable("imei") String imei){
        return thirdPartyApiService.getAuthenticityPhoneByImei(imei);
    }

}
