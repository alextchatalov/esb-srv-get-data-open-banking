package com.getdata.dataprovider.gateway;

import com.getdata.core.model.Participant;
import com.getdata.core.model.ParticipantStatus;
import com.getdata.core.usecase.FindProductsAndServicesActiveBoundary;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.dataprovider.mapper.ParticipantEntityToParticipantMapper;
import com.getdata.dataprovider.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindProductsAndServicesActiveGateway implements FindProductsAndServicesActiveBoundary {

    private final ParticipantRepository repository;
    private final ParticipantEntityToParticipantMapper participantEntityToParticipantMapper;

    @Override
    public List<Participant> execute() {

        List<ParticipantEntity> participantEntities = repository.findByStatus(ParticipantStatus.ACTIVE);
        return participantEntities.stream().map(participantEntityToParticipantMapper::convert).collect(Collectors.toList());
    }
}
