package com.api.NormalizeService.mapper;


import com.api.NormalizeService.model.PartnerMessage.PartnerMessageAudit;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PartnerMessageMapper {
    void save(String message, String imei, String specsId);
    public PartnerMessageAudit getMessageByImei(String imei);
}
