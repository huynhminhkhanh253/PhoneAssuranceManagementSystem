package com.api.NormalizeService.service;

import com.api.NormalizeService.mapper.PartnerMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PartnerMessageService implements MessageServices {
    @Autowired
    PartnerMessageMapper partnerMessageMapper;
    public void addToDb(String message, String imei, String specsId){
        if(partnerMessageMapper.getMessageByImei(imei) == null) partnerMessageMapper.save(message, imei, specsId);
    }

}
