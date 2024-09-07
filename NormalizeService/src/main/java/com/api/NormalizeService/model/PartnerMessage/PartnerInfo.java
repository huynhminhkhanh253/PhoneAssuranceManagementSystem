package com.api.NormalizeService.model.PartnerMessage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerInfo {
    private String partnerId;
    private PartnerLocation partnerLocation;

}
