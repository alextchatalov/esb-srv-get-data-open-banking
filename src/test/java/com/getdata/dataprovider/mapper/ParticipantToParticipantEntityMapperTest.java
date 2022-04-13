package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Participant;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BrandEntityFixture;
import com.getdata.fixtures.resource.ParticipantEntityFixture;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ParticipantToParticipantEntityMapperTest {

    @InjectMocks
    ParticipantToParticipantEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_participant_mapper_When_call_convert_to_participant_entity_Then_return_participant_entity() {
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        final ParticipantEntity participantEntityMock = Fixture.from(ParticipantEntity.class).gimme(ParticipantEntityFixture.VALID);
        final BrandEntity brandEntityMock = Fixture.from(BrandEntity.class).gimme(BrandEntityFixture.VALID);

        final ParticipantEntity participantEntity = mapper.convert(participantMock, Arrays.asList(brandEntityMock));

        assertThat(participantEntity.getOrganisationId()).isEqualTo(participantEntityMock.getOrganisationId());
        assertThat(participantEntity.getStatus()).isEqualTo(participantEntityMock.getStatus());
        assertThat(participantEntity.getOrganisationName()).isEqualTo(participantEntityMock.getOrganisationName());
        assertThat(participantEntity.getCustomerFriendlyName()).isEqualTo(participantEntityMock.getCustomerFriendlyName());
        assertThat(participantEntity.getApiResources().get(0).getApiFamilyType()).isEqualTo(participantEntityMock.getApiResources().get(0).getApiFamilyType());
        assertThat(participantEntity.getApiResources().get(0).getApiVersion()).isEqualTo(participantEntityMock.getApiResources().get(0).getApiVersion());
        assertThat(participantEntity.getApiResources().get(0).getApiEndpoint().get(0).getEndpoint()).isEqualTo(participantEntityMock.getApiResources().get(0).getApiEndpoint().get(0).getEndpoint());
    }
}
