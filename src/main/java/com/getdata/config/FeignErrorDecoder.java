package com.getdata.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getdata.exception.model.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(final String methodKey, final Response response) {

        final ExceptionMessage message;
        try (final InputStream bodyIs = response.body()
                .asInputStream()) {
            final ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (final IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 404:
                log.warn("Not Found [" + response.status() + "] Uri " + message.getMessage());
            default:
                return errorDecoder.decode(methodKey, response);
        }

    }
}
