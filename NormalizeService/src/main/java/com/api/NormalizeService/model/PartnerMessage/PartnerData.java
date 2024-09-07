package com.api.NormalizeService.model.PartnerMessage;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerData {
    private String partnerName;
    private PartnerInfo partnerInfo;
    private String exclusiveOffer;
}
