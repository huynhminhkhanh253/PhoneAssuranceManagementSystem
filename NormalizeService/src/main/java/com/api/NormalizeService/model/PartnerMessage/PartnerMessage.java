package com.api.NormalizeService.model.PartnerMessage;

import com.api.NormalizeService.model.GuaranteeEvent;
import com.api.NormalizeService.model.commonData.CommonMessage;
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
        return guaranteeEvent.toBuilder()
                .partnerName(getPartnerData().getPartnerName())
                .partnerId(getPartnerData().getPartnerInfo().getPartnerId())
                .partnerCity(getPartnerData().getPartnerInfo().getPartnerLocation().getCity())
                .partnerState(getPartnerData().getPartnerInfo().getPartnerLocation().getState())
                .partnerZipcode(getPartnerData().getPartnerInfo().getPartnerLocation().getZipCode())
                .exclusiveOffer(getPartnerData().getExclusiveOffer())
                .build();
    }
}
