package com.api.NormalizeService.service;

import com.api.NormalizeService.mapper.OnlineMessageMapper;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OnlineMessageService implements MessageServices {
    @Autowired
    OnlineMessageMapper onlineMessageMapper;
    //Check data format
    public void addToDb(String message, String imei, String specs_id){
        if(onlineMessageMapper.getMessageByImei(imei) == null) onlineMessageMapper.save(message, imei, specs_id);
    }
/*    public void saveOnlineMessageToDb(OnlineMessage onlineMessage){
        onlineMessageRepository.save(onlineMessage);
    }*/

}
