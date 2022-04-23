package com.getdata.core.usecase;

import com.getdata.core.model.Request;
import com.getdata.core.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestProductsAndServicesUserCase {

    private final RequestProductsAndServicesBoundary requestProductsAndServicesBoundary;

    public List<Response> execute(final List<Request> apis) {
        final List<Response> responseProductsAndServices = new ArrayList<>();

        apis.forEach(request -> {
            final String productsAndServicesResponse = requestProductsAndServicesBoundary.execute(request.getUrl() + "?page=1&page-size=1000");
            if (productsAndServicesResponse != null) {
                final Response response = Response.builder()
                        .participant(request.getParticipant())
                        .lastRequest(request.getLastRequest())
                        .category(request.getCategory())
                        .url(request.getUrl())
                        .version(request.getVersion())
                        .response(productsAndServicesResponse)
                        .build();

                responseProductsAndServices.add(response);
            }
        });

        return responseProductsAndServices;
    }
}
