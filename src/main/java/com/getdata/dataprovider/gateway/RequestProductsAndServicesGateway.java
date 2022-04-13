package com.getdata.dataprovider.gateway;

import com.getdata.core.usecase.RequestProductsAndServicesBoundary;
import com.getdata.dataprovider.client.RequestProductsAndServicesClient;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequestProductsAndServicesGateway implements RequestProductsAndServicesBoundary {

    private final RequestProductsAndServicesClient client;

    @Override
    public String execute(final String url) {

        try {
            final Response response = client.request(URI.create(url));

            if (response.status() == 200) {
                return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }
            return null;

        } catch (final Exception e) {
            log.error("Error while calling requestProductsAndServices", e.getMessage());
            return null;
        }
    }
}
