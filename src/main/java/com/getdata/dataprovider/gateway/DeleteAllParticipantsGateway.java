package com.getdata.dataprovider.gateway;

import com.getdata.core.usecase.DeleteAllParticipantsBoundary;
import com.getdata.dataprovider.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAllParticipantsGateway implements DeleteAllParticipantsBoundary {

    private final ParticipantRepository repository;

    @Override
    public void execute() {
        repository.deleteAll();
    }
}
