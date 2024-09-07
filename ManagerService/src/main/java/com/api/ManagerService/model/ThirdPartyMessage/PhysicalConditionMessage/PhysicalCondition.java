package com.api.ManagerService.model.ThirdPartyMessage.PhysicalConditionMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalCondition {
    private String screenHealth;
    private String batteryHealth;
    private String externalDamage;
    private String cameraFunctionality;
    private String speakerMicrophoneHealth;
    private String sensorHealth;
    private Dimensions dimensions;
    private String weight;

    public float getBatteryHealthNumber(){
        return Float.parseFloat(getBatteryHealth().replaceAll("[^0-9\\.]+", ""));
    }
}
