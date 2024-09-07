package com.api.ManagerService.model.commonData;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@Component
@RequiredArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Specs {
    private long id;
    private double screenSize;
    private String ram;
    private String cpu;
    private String storage;
    private Camera camera;
    private String camera_main;
    private String camera_front;
    private String battery;
}

