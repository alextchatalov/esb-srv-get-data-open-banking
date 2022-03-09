package com.getdata.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Participant;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CreateParticipantsUserCaseTest {

    @Mock
    CreateParticipantsBoundary createRequestBoundary;

    @InjectMocks
    CreateParticipantsUserCase createParticipantsUserCase;

    @Mock
    ProcessJsonUserCase processJsonUserCase;

    @Mock
    DeleteAllParticipantsBoundary deleteAllParticipantsBoundary;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_valid_participant_json_When_CreateParticipant_then_Save_new_participant_Sucess() throws IOException {
        final String participantJsonMock = new String(Files.readAllBytes(Paths.get("src/test/resources/jsons/participant.json")));

        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        when(processJsonUserCase.execute(any(String.class))).thenReturn(Arrays.asList(participantMock));
        when(createRequestBoundary.save(any(Participant.class))).thenReturn(participantMock);
        doNothing().when(deleteAllParticipantsBoundary).execute();

        final List<Participant> participants = createParticipantsUserCase.execute(participantJsonMock);
        Assert.assertEquals(participants.get(0), participantMock);
    }
}
