package com.api.NormalizeService.model.PhysicalMessage;

import com.api.NormalizeService.model.GuaranteeEvent;
import com.api.NormalizeService.model.commonData.CommonMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhysicalMessage extends CommonMessage {
    PhysicalData physicalData;
    @Override
    public GuaranteeEvent convertToGuaranteeEvent() {
            GuaranteeEvent guaranteeEvent = super.convertToGuaranteeEvent();
            return guaranteeEvent.toBuilder()
                    .storeName(getPhysicalData().getStoreName())
                    .physical_price(getPhysicalData().getPrice())
                    .storeId(getPhysicalData().getStoreId())
                    .build();
        }
}
