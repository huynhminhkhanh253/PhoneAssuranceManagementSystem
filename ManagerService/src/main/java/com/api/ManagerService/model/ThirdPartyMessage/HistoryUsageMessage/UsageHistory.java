package com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageHistory {
    private String timeStamp;
    private String event;
    private String details;

    @JsonIgnore
    public float getOsVersion(){
        return Float.parseFloat(getDetails().replaceAll("[^0-9\\.]+", ""));
    }
}
