package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Participant;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ParticipantEntityFixture;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ParticipantEntityToParticipantMapperTest {

    @InjectMocks
    ParticipantEntityToParticipantMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_participant_entity_mapper_When_call_convert_to_participant_Then_return_participant() {
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        final ParticipantEntity participantEntityMock = Fixture.from(ParticipantEntity.class).gimme(ParticipantEntityFixture.VALID);

        Participant participant = mapper.convert(participantEntityMock);
        assertThat(participant).isEqualTo(participantMock);
    }
}
