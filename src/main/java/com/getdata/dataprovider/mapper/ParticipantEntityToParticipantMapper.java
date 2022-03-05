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
import java.util.stream.Collectors;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class ParticipantEntityToParticipantMapper implements Converter<ParticipantEntity, Participant> {

    @Override
    @NonNull
    public Participant convert(final ParticipantEntity participantEntity) {
        final Participant participant = Participant.builder()
                .organisationId(participantEntity.getOrganisationId())
                .status(participantEntity.getStatus())
                .organisationName(participantEntity.getOrganisationName())
                .customerFriendlyName(participantEntity.getCustomerFriendlyName())
                .build();

        participant.setApiResources(createListOfApiResource(participantEntity.getApiResources()));
        return participant;
    }

    private List<ApiResource> createListOfApiResource(final List<ApiResourceEntity> apiResources) {

        if (apiResources == null || apiResources.isEmpty()) {
            return new ArrayList<>();
        }

        final List<ApiResource> apiResourceList = new ArrayList<>();
        apiResources.forEach(api -> {
            apiResourceList.add(ApiResource.builder()
                    .apiFamilyType(api.getApiFamilyType())
                    .apiVersion(api.getApiVersion())
                    .apiEndpoint(createApiEndpoint(api.getApiEndpoint()))
                    .build());
        });
        return apiResourceList;
    }

    private List<String> createApiEndpoint(List<ApiEndPointEntity> apiEndpoint) {
        if (apiEndpoint == null || apiEndpoint.isEmpty()) {
            return new ArrayList<>();
        }
        return apiEndpoint.stream().map(ApiEndPointEntity::getEndpoint).collect(Collectors.toList());
    }

}
