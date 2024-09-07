package com.api.NormalizeService.mapper;


import com.api.NormalizeService.model.PhysicalMessage.PhysicalMessageAudit;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PhysicalMessageMapper {
    void save(String message, String imei, String specsId);
    public PhysicalMessageAudit getMessageByImei(String imei);
}
