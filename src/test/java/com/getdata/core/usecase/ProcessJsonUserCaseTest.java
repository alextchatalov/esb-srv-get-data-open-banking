package com.getdata.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Participant;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ProcessJsonUserCaseTest {

    @InjectMocks
    ProcessJsonUserCase processJsonUserCase;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_valid_participant_json_When_process_json_to_list_of_participants_then_return_list_of_participants() throws IOException {
        final String participantJsonMock = new String(Files.readAllBytes(Paths.get("src/test/resources/jsons/participant.json")));
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);

        final List<Participant> participants = processJsonUserCase.execute(participantJsonMock);
        assertThat(participants.get(0).getOrganisationId()).isEqualTo(participantMock.getOrganisationId());
        assertThat(participants.get(0).getStatus()).isEqualTo(participantMock.getStatus());
        assertThat(participants.get(0).getOrganisationName()).isEqualTo(participantMock.getOrganisationName());
        assertThat(participants.get(0).getCustomerFriendlyName()).isEqualTo(participantMock.getCustomerFriendlyName());
        assertThat(participants.get(0).getApiResources().get(0).getApiFamilyType()).isEqualTo(participantMock.getApiResources().get(0).getApiFamilyType());
        assertThat(participants.get(0).getApiResources().get(0).getApiVersion()).isEqualTo(participantMock.getApiResources().get(0).getApiVersion());
        assertThat(participants.get(0).getApiResources().get(0).getApiEndpoint().get(0)).isEqualTo(participantMock.getApiResources().get(0).getApiEndpoint().get(0));
    }

    @Test
    void given_participant_json_with_out_OrgDomainClaims_When_process_json_to_list_of_participants_then_return_list_of_participants_with_out_OrgDomainClaims() throws IOException {
        final String participantJsonMock = new String(Files.readAllBytes(Paths.get("src/test/resources/jsons/participantWithOutOrgDomainClaims.json")));
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID_WITHOUT_ORGANISATION_CLAIMS);

        final List<Participant> participants = processJsonUserCase.execute(participantJsonMock);
        assertThat(participants.get(0).getOrganisationId()).isEqualTo(participantMock.getOrganisationId());
        assertThat(participants.get(0).getStatus()).isEqualTo(participantMock.getStatus());
        assertThat(participants.get(0).getOrganisationName()).isEqualTo(participantMock.getOrganisationName());
        assertThat(participants.get(0).getCustomerFriendlyName()).isEqualTo(participantMock.getCustomerFriendlyName());
        assertThat(participants.get(0).getApiResources().get(0).getApiFamilyType()).isEqualTo(participantMock.getApiResources().get(0).getApiFamilyType());
        assertThat(participants.get(0).getApiResources().get(0).getApiVersion()).isEqualTo(participantMock.getApiResources().get(0).getApiVersion());
        assertThat(participants.get(0).getApiResources().get(0).getApiEndpoint().get(0)).isEqualTo(participantMock.getApiResources().get(0).getApiEndpoint().get(0));
    }
}
