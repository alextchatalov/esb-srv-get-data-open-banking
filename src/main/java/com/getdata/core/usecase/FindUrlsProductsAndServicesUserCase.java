package com.getdata.core.usecase;

import com.getdata.core.model.Category;
import com.getdata.core.model.Participant;
import com.getdata.core.model.Request;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        final List<Request> apis = new ArrayList<>();
        final Map<Participant, List<String>> apisProductAndServices = findProductsAndServicesActiveUserCase.execute();
        for (final Map.Entry<Participant, List<String>> participantMap : apisProductAndServices.entrySet()) {
            for (final String api : participantMap.getValue()) {
                try {

                    final String version = getVersion(api);
                    final Category category = Category.getCategoryFromUrl(api);

//                    Request request = Request.builder()
//                            .participant(participantMap.getKey())
//                            .category(category)
//                            .version(version)
//                            .url(api)
//                            .lastRequest(LocalDateTime.now())
//                            .build();
//
//                    apis.add(request);
                } catch (final Exception e) {
                    log.error("Error to create Request: " + e);
                }
            }

        }

        return apis;
    }

    private String getVersion(final String api) {

        try {
            final int indexVersionStart = StringUtils.ordinalIndexOf(api, "/", 5);
            final int indexVersionEnd = StringUtils.ordinalIndexOf(api, "/", 6);
            final String version = api.substring(indexVersionStart + 1, indexVersionEnd);
            return version;
        } catch (final Exception e) {
            log.warn("Can not find version from api");
            return null;
        }
    }

}
