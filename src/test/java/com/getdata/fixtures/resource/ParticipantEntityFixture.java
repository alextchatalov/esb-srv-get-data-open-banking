package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.ParticipantStatus;
import com.getdata.dataprovider.entity.ApiEndPointEntity;
import com.getdata.dataprovider.entity.ApiResourceEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;

import java.util.Arrays;
import java.util.List;

public class ParticipantEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        ParticipantEntity participantMock = ParticipantEntity.builder()
                .organisationId("123")
                .status(ParticipantStatus.ACTIVE)
                .organisationName("test")
                .customerFriendlyName("test")
                .build();

        Fixture.of(ParticipantEntity.class).addTemplate(VALID, new Rule() {{
            add(ParticipantEntity.Fields.organisationId, participantMock.getOrganisationId());
            add(ParticipantEntity.Fields.status, participantMock.getStatus());
            add(ParticipantEntity.Fields.organisationName, participantMock.getOrganisationName());
            add(ParticipantEntity.Fields.customerFriendlyName, participantMock.getCustomerFriendlyName());
            add(ParticipantEntity.Fields.apiResources, Arrays.asList(createApiResources(participantMock)));

        }});
    }

    private ApiResourceEntity createApiResources(ParticipantEntity participantMock) {

        ApiResourceEntity apiResourceEntityMock = ApiResourceEntity.builder()
                .apiFamilyType("test")
                .apiVersion("1")
                .participant(participantMock)
                .build();

        List<ApiEndPointEntity> apiEndPointEntity = createApiEndPointEntity(apiResourceEntityMock);
        apiResourceEntityMock.setApiEndpoint(apiEndPointEntity);
        return apiResourceEntityMock;
    }

    private List<ApiEndPointEntity> createApiEndPointEntity(ApiResourceEntity apiResourceEntityMock) {

        return Arrays.asList(ApiEndPointEntity.builder()
                .id(1L)
                .endpoint("123")
                .apiResource(apiResourceEntityMock)
                .build());
    }

}
