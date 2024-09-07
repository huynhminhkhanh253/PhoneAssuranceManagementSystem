package com.api.ManagerService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuaranteeEventDatabaseAudit {
    String id;
    String imei;
    String rawMessage;
    String dateCreated;
    String dateUpdate;
}
