package com.api.ManagerService.model.commonData;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Camera {
    private String main;
    private String front;
}
