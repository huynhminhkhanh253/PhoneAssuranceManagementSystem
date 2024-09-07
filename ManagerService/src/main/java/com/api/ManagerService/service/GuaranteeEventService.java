package com.api.ManagerService.service;

import com.api.ManagerService.mapper.GuaranteeEventMapper;
import com.api.ManagerService.model.GuaranteeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuaranteeEventService {
    @Autowired
    GuaranteeEventMapper guaranteeEventMapper;
    public void addGuaranteeEventToDb(GuaranteeEvent guaranteeEvent, String raw_message){
        if(guaranteeEventMapper.getGuaranteeEventMessageByImei(guaranteeEvent.getImei()) == null){
            guaranteeEventMapper.addToDb(guaranteeEvent, raw_message);
        }
    }
}
