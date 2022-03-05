package com.getdata.dataprovider.mapper;

import com.getdata.core.model.ApiResource;
import com.getdata.core.model.Participant;
import com.getdata.dataprovider.entity.ApiEndPointEntity;
import com.getdata.dataprovider.entity.ApiResourceEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class ParticipantToParticipantEntityMapper implements Converter<Participant, ParticipantEntity> {

    @Override
    @NonNull
    public ParticipantEntity convert(final Participant participant) {
        final ParticipantEntity participantEntity = ParticipantEntity.builder()
                .organisationId(participant.getOrganisationId())
                .status(participant.getStatus())
                .organisationName(participant.getOrganisationName())
                .customerFriendlyName(participant.getCustomerFriendlyName())
                .build();

        participantEntity.setApiResources(createListOfApiResource(participant.getApiResources(), participantEntity));
        return participantEntity;
    }

    private List<ApiResourceEntity> createListOfApiResource(final List<ApiResource> apiResources, final ParticipantEntity participantEntity) {
        if (apiResources == null || apiResources.isEmpty()) {
            return new ArrayList<>();
        }
        final List<ApiResourceEntity> apiResourceEntityList = new ArrayList<>();
        apiResources.forEach(api -> {
            ApiResourceEntity apiResourceEntity = ApiResourceEntity.builder()
                    .participant(participantEntity)
                    .apiFamilyType(api.getApiFamilyType())
                    .apiVersion(api.getApiVersion())
                    .build();

            apiResourceEntity.setApiEndpoint(createListOfApiEndpoint(api.getApiEndpoint(), apiResourceEntity));
            apiResourceEntityList.add(apiResourceEntity);
        });
        return apiResourceEntityList;
    }

    private List<ApiEndPointEntity> createListOfApiEndpoint(List<String> apiEndpoint, ApiResourceEntity apiResourceEntity) {

        final List<ApiEndPointEntity> apiEndPointEntityList = new ArrayList<>();

        apiEndpoint.forEach(api -> apiEndPointEntityList.add(ApiEndPointEntity.builder()
                .apiResource(apiResourceEntity)
                .endpoint(api)
                .build()));

        return apiEndPointEntityList;
    }

}
