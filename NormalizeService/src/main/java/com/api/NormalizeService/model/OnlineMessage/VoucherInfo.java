package com.api.NormalizeService.model.OnlineMessage;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
public class VoucherInfo {
    private String voucherCode;
    private UUID voucherId;
    private int discountPercentage;
    private String validUntil;
}
