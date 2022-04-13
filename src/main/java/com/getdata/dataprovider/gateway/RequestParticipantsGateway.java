package com.getdata.dataprovider.gateway;

import com.getdata.core.usecase.RequestParticipantsBoundary;
import com.getdata.dataprovider.client.ParticipantClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestParticipantsGateway implements RequestParticipantsBoundary {

    private final ParticipantClient client;

    @Override
    public String execute() {
        return client.getParticipantsFromRepository();
    }
}
