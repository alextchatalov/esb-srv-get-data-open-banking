package com.getdata.dataprovider.gateway;

import com.getdata.dataprovider.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FindCategoryActiveGatewayTest {

    @InjectMocks
    FindProductsAndServicesActiveGateway findProductsAndServicesActiveGateway;

    @Mock
    ParticipantRepository repository;

    @Test
    void given_non_params_When_find_all_apis_active_Then_return_all_participants_active() {
//        final Participant participants = ParticipantFixute.validParticipant();
//        final ParticipantEntity participantEntity = ParticipantConverter.participantToParticipantEntity(participants);
//        when(repository.findByStatus(ParticipantStatus.ACTIVE)).thenReturn(Arrays.asList(participantEntity));
//        final List<Participant> participantsActive = findProductsAndServicesActiveGateway.execute();
//        Assert.assertEquals(Arrays.asList(participants), participantsActive);
    }
}
