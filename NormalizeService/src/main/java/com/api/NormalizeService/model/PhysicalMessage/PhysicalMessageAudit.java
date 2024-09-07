package com.api.NormalizeService.model.PhysicalMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalMessageAudit {
    String id;
    String imei;
    String rawMessage;
    String dateCreated;
    String dateUpdate;
}

