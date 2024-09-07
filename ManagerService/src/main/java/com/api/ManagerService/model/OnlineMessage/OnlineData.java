package com.api.ManagerService.model.OnlineMessage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OnlineData  {
    private double price;
    private UUID historyID;
    private String Channel;
}
