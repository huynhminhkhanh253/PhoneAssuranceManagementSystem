package com.api.ManagerService.model.ThirdPartyMessage.HistoryUsageMessage;

import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryUsageMessage extends ThirdPartyMessage {
    private ArrayList<UsageHistory> usageHistories;
}
