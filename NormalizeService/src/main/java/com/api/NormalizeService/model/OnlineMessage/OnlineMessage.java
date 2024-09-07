package com.api.NormalizeService.model.OnlineMessage;


import com.api.NormalizeService.model.GuaranteeEvent;
import com.api.NormalizeService.model.commonData.CommonMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineMessage extends CommonMessage {
    private OnlineData onlineData;
    private VoucherInfo voucherInfo;

    @Override
    public GuaranteeEvent convertToGuaranteeEvent() {
        GuaranteeEvent guaranteeEvent = super.convertToGuaranteeEvent();
        return guaranteeEvent.toBuilder()
                .online_price(getOnlineData().getPrice())
                .historyID(getOnlineData().getHistoryID())
                .Channel(getOnlineData().getChannel())
                .voucherCode(getVoucherInfo().getVoucherCode())
                .voucherId(getVoucherInfo().getVoucherId())
                .discountPercentage(getVoucherInfo().getDiscountPercentage())
                .validUntil(getVoucherInfo().getValidUntil())
                .build();
    }
}
