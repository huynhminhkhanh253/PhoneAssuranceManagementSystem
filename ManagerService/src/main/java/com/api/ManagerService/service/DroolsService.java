package com.api.ManagerService.service;

import com.api.ManagerService.model.ThirdPartyMessage.ThirdPartyMessage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DroolsService {

    private final KieContainer kieContainer;

    @Autowired
    public DroolsService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public HashMap<String, ArrayList<String>> executeRule(ThirdPartyMessage thirdPartyMessage) {
        KieSession kieSession = kieContainer.newKieSession();
        //init result variable
        ArrayList<String> ineligibleResults = new ArrayList<>();
        ArrayList<String> pendingResults = new ArrayList<>();


        kieSession.setGlobal("ineligibleResults", ineligibleResults );
        kieSession.setGlobal("pendingResults", pendingResults);
        kieSession.insert(thirdPartyMessage);
        kieSession.fireAllRules();
        kieSession.dispose();

        // prepare result map
        HashMap<String, ArrayList<String>> results = new HashMap<>();
        results.put("ineligibleResults", ineligibleResults);
        results.put("pendingResults", pendingResults);

        return results;
    }
}
