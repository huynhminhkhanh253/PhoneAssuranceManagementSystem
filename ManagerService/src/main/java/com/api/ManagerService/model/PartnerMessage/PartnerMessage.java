package com.api.ManagerService.model.PartnerMessage;

import com.api.ManagerService.model.GuaranteeEvent;
import com.api.ManagerService.model.commonData.CommonMessage;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PartnerData")
public class PartnerMessage extends CommonMessage {
    @XmlElement
    private PartnerData partnerData;

    @Override
    public GuaranteeEvent convertToGuaranteeEvent() {
        GuaranteeEvent guaranteeEvent = super.convertToGuaranteeEvent();
        guaranteeEvent.toBuilder()
                .partnerName(getPartnerData().getPartnerName())
                .partnerId(getPartnerData().getPartnerInfo().getPartnerId())
                .partnerCity(getPartnerData().getPartnerInfo().getPartnerLocation().getCity())
                .partnerState(getPartnerData().getPartnerInfo().getPartnerLocation().getState())
                .partnerZipcode(getPartnerData().getPartnerInfo().getPartnerLocation().getZipCode())
                .exclusiveOffer(getPartnerData().getExclusiveOffer());
        return guaranteeEvent;
    }
}
