package com.api.NormalizeService.mapper;


import com.api.NormalizeService.model.OnlineMessage.OnlineMessageAudit;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OnlineMessageMapper {
    void save(String message, String imei, String specsId);

    OnlineMessageAudit getMessageByImei(String imei);
}
