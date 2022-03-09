package com.getdata.dataprovider.gateway;

import com.getdata.dataprovider.repository.ParticipantRepository;
import com.getdata.fixtures.FixtureLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class DeleteAllParticipantsGatewayTest {

    @InjectMocks
    DeleteAllParticipantsGateway deleteAllParticipantsGateway;

    @Mock
    ParticipantRepository repository;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_nothing_When_delete_all_participants_then_delete_all_success() {

        doNothing().when(repository).deleteAll();

        assertDoesNotThrow(() -> deleteAllParticipantsGateway.execute());
    }
}
