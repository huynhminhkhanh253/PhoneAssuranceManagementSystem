package com.api.NormalizeService.model.PartnerMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerMessageAudit {
    String id;
    String imei;
    String rawMessage;
    String dateCreated;
    String dateUpdate;
}
