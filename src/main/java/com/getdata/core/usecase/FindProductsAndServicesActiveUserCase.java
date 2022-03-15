package com.getdata.core.usecase;

import com.getdata.core.model.ApiResource;
import com.getdata.core.model.Participant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class FindProductsAndServicesActiveUserCase {

    FindProductsAndServicesActiveBoundary findProductsAndServicesActiveBoundary;

    public Map<Participant, List<String>> execute() {

        List<Participant> participantsActive = findProductsAndServicesActiveBoundary.execute();
        Map<Participant, List<String>> apisProductAndServices = new HashMap<>();
        for (Participant participant : participantsActive) {
            List<ApiResource> apiResources = participant.getApiResources().stream().filter(apiResource -> "products-services".equals(apiResource.getApiFamilyType())).collect(Collectors.toList());
            for (ApiResource api : apiResources) {
                apisProductAndServices.put(participant, api.getApiEndpoint());
            }
        }

        return apisProductAndServices;
    }
}
