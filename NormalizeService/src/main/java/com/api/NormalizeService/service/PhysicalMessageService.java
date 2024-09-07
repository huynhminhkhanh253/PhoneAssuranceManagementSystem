package com.api.NormalizeService.service;
import com.api.NormalizeService.mapper.PhysicalMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhysicalMessageService implements MessageServices {
    @Autowired
    PhysicalMessageMapper physicalMessageMapper;
    //Check data format
    public void addToDb(String message, String imei, String specsId){
        if(physicalMessageMapper.getMessageByImei(imei) == null) physicalMessageMapper.save(message, imei, specsId);
    }
}
