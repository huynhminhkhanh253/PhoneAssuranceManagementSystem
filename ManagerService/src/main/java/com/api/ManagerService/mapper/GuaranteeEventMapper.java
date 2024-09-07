package com.api.ManagerService.mapper;

import com.api.ManagerService.model.GuaranteeEvent;
import com.api.ManagerService.model.GuaranteeEventDatabaseAudit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuaranteeEventMapper {
    void addToDb(GuaranteeEvent guaranteeEvent, String raw_message);

    GuaranteeEventDatabaseAudit getGuaranteeEventMessageByImei(String imei);
}
