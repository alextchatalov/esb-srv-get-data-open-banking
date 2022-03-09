package com.getdata.dataprovider.gateway;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Participant;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.dataprovider.mapper.ParticipantEntityToParticipantMapper;
import com.getdata.dataprovider.mapper.ParticipantToParticipantEntityMapper;
import com.getdata.dataprovider.repository.ParticipantRepository;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ParticipantEntityFixture;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateParticipantsGatewayTest {

    @InjectMocks
    CreateParticipantsGateway createParticipantsGateway;

    @Mock
    ParticipantToParticipantEntityMapper participantToParticipantEntityMapper;

    @Mock
    ParticipantEntityToParticipantMapper participantEntityToParticipantMapper;

    @Mock
    ParticipantRepository repository;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_valid_participant_When_save_then_save_success() {

        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        final ParticipantEntity participantEntityMock = Fixture.from(ParticipantEntity.class).gimme(ParticipantEntityFixture.VALID);

        when(participantToParticipantEntityMapper.convert(any(Participant.class))).thenReturn(participantEntityMock);
        when(participantEntityToParticipantMapper.convert(any(ParticipantEntity.class))).thenReturn(participantMock);
        when(repository.save(any(ParticipantEntity.class))).thenReturn(participantEntityMock);

        final Participant participantSaved = createParticipantsGateway.save(participantMock);
        assertThat(participantSaved).isEqualTo(participantMock);
    }
}
