package com.api.ManagerService.model.PhysicalMessage;

import com.api.ManagerService.model.GuaranteeEvent;
import com.api.ManagerService.model.commonData.CommonMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhysicalMessage extends CommonMessage {
    PhysicalData physicalData;
    @Override
    public GuaranteeEvent convertToGuaranteeEvent() {
            GuaranteeEvent guaranteeEvent = super.convertToGuaranteeEvent();
            guaranteeEvent.toBuilder()
                    .storeName(getPhysicalData().getStoreName())
                    .physical_price(getPhysicalData().getPrice())
                    .storeId(getPhysicalData().getStoreId());
            return guaranteeEvent;
        }
}
