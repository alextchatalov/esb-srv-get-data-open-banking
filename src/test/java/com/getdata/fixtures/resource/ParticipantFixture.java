package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.ApiResource;
import com.getdata.core.model.Participant;
import com.getdata.core.model.ParticipantStatus;

import java.util.Arrays;

public class ParticipantFixture implements TemplateLoader {

    public static final String VALID = "valid";
    public static final String VALID_WITHOUT_ORGANISATION_CLAIMS = "valid without organisation claims";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Participant.class).addTemplate(VALID, new Rule() {{
            add(Participant.Fields.organisationId, "123");
            add(Participant.Fields.status, ParticipantStatus.ACTIVE);
            add(Participant.Fields.organisationName, "test");
            add(Participant.Fields.customerFriendlyName, "test");
            add(Participant.Fields.customerFriendlyLogoUri, "test");
            add(Participant.Fields.apiResources, Arrays.asList(createApiResources()));

        }});

        Fixture.of(Participant.class).addTemplate(VALID_WITHOUT_ORGANISATION_CLAIMS, new Rule() {{
            add(Participant.Fields.organisationId, "123");
            add(Participant.Fields.status, ParticipantStatus.INACTIVE);
            add(Participant.Fields.organisationName, "test");
            add(Participant.Fields.customerFriendlyName, "test");
            add(Participant.Fields.apiResources, Arrays.asList(createApiResources()));

        }});
    }

    private ApiResource createApiResources() {

        return ApiResource.builder()
                .apiFamilyType("test")
                .apiVersion("1")
                .apiEndpoint(Arrays.asList("123"))
                .build();
    }

}
