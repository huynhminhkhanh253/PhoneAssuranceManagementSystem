package com.api.NormalizeService.service;

import com.api.NormalizeService.mapper.OnlineMessageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class OnlineMessageServiceTests {
    @Mock private OnlineMessageMapper onlineMessageMapper;

    @InjectMocks
    private OnlineMessageService onlineMessageService;

    @Autowired


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToDb() {
        String message = "";
        String imei = "";
        String specs_id = "";
        onlineMessageService.addToDb(message, imei, specs_id);
        // test interation with mock mapper
    }
    //String message, String imei, String specs_id
}
