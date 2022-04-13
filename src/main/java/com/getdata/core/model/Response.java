package com.getdata.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@Slf4j
public class Response {

    private Category category;
    private String url;
    private String response;
    private String version;
    private Participant participant;
    @EqualsAndHashCode.Exclude
    private LocalDateTime lastRequest;

    public Root getObjectFromJsonResponse() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            final Root rootConverted = objectMapper.readValue(this.response, Root.class);
            rootConverted.getData().getBrand().setParticipant(this.getParticipant());
            return rootConverted;
        } catch (final JsonProcessingException e) {
            System.out.println(this.response);
            log.error("Error to map json response to object: " + e.getMessage());
            log.error("Response: " + this.response);
            return null;
        }
    }

}
