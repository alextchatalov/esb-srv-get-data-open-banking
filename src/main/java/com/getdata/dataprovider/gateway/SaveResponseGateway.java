package com.getdata.dataprovider.gateway;

import com.getdata.core.model.Data;
import com.getdata.core.usecase.SaveResponseBoundary;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.dataprovider.mapper.DataToDataEntityMapper;
import com.getdata.dataprovider.repository.DataRepository;
import com.getdata.dataprovider.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveResponseGateway implements SaveResponseBoundary {


    private final DataRepository dataRepository;
    private final ParticipantRepository participantRepository;
    private final DataToDataEntityMapper dataToDataEntityMapper;

    @Override
    public void execute(final Data data) {

        final ParticipantEntity participantEntity = participantRepository.findById(data.getBrand().getParticipant().getOrganisationId())
                .orElseThrow(() ->
                        new RuntimeException("Participant not found by organisation Id: " + data.getBrand().getParticipant().getOrganisationId()));

        final DataEntity dataEntity = dataToDataEntityMapper.convert(data, participantEntity);
        try {
            dataRepository.save(dataEntity);
        } catch (final Exception e) {
            log.error("Error while saving data", e);
            log.error("Data: {}", data);

        }

    }
}
