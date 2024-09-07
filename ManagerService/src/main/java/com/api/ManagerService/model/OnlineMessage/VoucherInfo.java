package com.api.ManagerService.model.OnlineMessage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class VoucherInfo {
    private String voucherCode;
    private UUID voucherId;
    private int discountPercentage;
    private String validUntil;
}
