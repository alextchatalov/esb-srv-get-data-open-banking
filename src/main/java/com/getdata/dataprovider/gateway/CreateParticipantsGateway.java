package com.getdata.dataprovider.gateway;

import com.getdata.core.model.Participant;
import com.getdata.core.usecase.CreateParticipantsBoundary;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.dataprovider.mapper.ParticipantEntityToParticipantMapper;
import com.getdata.dataprovider.mapper.ParticipantToParticipantEntityMapper;
import com.getdata.dataprovider.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateParticipantsGateway implements CreateParticipantsBoundary {

    private final ParticipantRepository repository;
    private final ParticipantToParticipantEntityMapper participantToParticipantEntityMapper;
    private final ParticipantEntityToParticipantMapper participantEntityToParticipantMapper;

    @Override
    public Participant save(final Participant participant) {
        final ParticipantEntity participantEntity = participantToParticipantEntityMapper.convert(participant);
        final ParticipantEntity participantSaved = repository.save(participantEntity);
        return participantEntityToParticipantMapper.convert(participantSaved);
    }
}
