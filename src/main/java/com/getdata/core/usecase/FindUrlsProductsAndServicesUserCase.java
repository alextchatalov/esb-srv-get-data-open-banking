package com.getdata.core.usecase;

import com.getdata.core.model.Category;
import com.getdata.core.model.Participant;
import com.getdata.core.model.Request;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class FindUrlsProductsAndServicesUserCase {

    @Autowired
    FindProductsAndServicesActiveUserCase findProductsAndServicesActiveUserCase;

    public List<Request> execute() {
        List<Request> apis = new ArrayList<>();
        Map<Participant, List<String>> apisProductAndServices = findProductsAndServicesActiveUserCase.execute();
        for (Map.Entry<Participant, List<String>> participantMap : apisProductAndServices.entrySet()) {
            for (String api : participantMap.getValue()) {
                try {

                    String version = getVersion(api);
                    Category category = Category.getCategoryFromUrl(api);

                    Request request = Request.builder()
                            .participant(participantMap.getKey())
                            .category(category)
                            .version(version)
                            .url(api)
                            .lastRequest(LocalDateTime.now())
                            .build();

                    apis.add(request);
                } catch (Exception e) {
                    log.error("Error to create Request: " + e);
                }
            }

        }

        return apis;
    }

    private String getVersion(String api) {

        try {
            int indexVersionStart = StringUtils.ordinalIndexOf(api, "/", 5);
            int indexVersionEnd = StringUtils.ordinalIndexOf(api, "/", 6);
            String version = api.substring(indexVersionStart + 1, indexVersionEnd);
            return version;
        } catch (Exception e) {
            log.warn("Can not find version from api");
            return null;
        }
    }

}
