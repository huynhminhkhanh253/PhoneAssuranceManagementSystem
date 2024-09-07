package com.api.ManagerService.service;

import com.api.ManagerService.model.ThirdPartyMessage.AuthenticityMessage.AuthenticityMessage;
import com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage.HistoryUsageMessage;
import com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage.PhysicalConditionMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "mock-api", url = "http://localhost:8082")
public interface ThirdPartyApiProxy {
    @GetMapping("/api/history-usage/{imei}")
    public HistoryUsageMessage getHistoryUsageByImei(@PathVariable("imei") String imei);

    @GetMapping("/api/physical-condition/{imei}")
    public PhysicalConditionMessage getPhysicalConditionByImei(@PathVariable("imei") String imei);

    @GetMapping("/api/authenticity-phone/{imei}")
    public AuthenticityMessage getAuthenticityPhoneByImei(@PathVariable("imei") String imei);
}
