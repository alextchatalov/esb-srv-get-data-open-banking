package com.getdata.core.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@EqualsAndHashCode
@ToString
@Slf4j
@Getter
public class Request {

    private Category category;
    private String url;
    private String version;
    private Participant participant;
    @EqualsAndHashCode.Exclude
    private LocalDateTime lastRequest;

    public static List<Request> createRequest(final Participant participant) {
        final List<Request> apis = new ArrayList<>();

        participant.getApiProductsServices().stream().forEach(apiResource -> {
            apiResource.getApiEndpoint().forEach(api -> {
                try {

                    final String version = getVersion(api);
                    final Category category = Category.getCategoryFromUrl(api);

                    final Request request = Request.builder()
                            .participant(participant)
                            .category(category)
                            .version(version)
                            .url(api)
                            .lastRequest(LocalDateTime.now())
                            .build();

                    apis.add(request);
                } catch (final Exception e) {
                    log.error("Error to create Request: " + e);
                }
            });
        });

        return apis;
    }

    private static String getVersion(final String api) {

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
