package com.getdata.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.ApiResource;
import com.getdata.core.model.Participant;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FindProductsAndServicesActiveUserCaseTest {

    @InjectMocks
    FindProductsAndServicesActiveUserCase findProductsAndServicesActiveUserCase;

    @Mock
    FindProductsAndServicesActiveBoundary findProductsAndServicesActiveBoundary;

    @Test
    void When_find_product_and_service_active_then_found_participant() {
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        when(findProductsAndServicesActiveBoundary.execute()).thenReturn(Arrays.asList(participantMock));
        Map<Participant, List<String>> mapParticipantAndApis = findProductsAndServicesActiveUserCase.execute();
        for (Map.Entry<Participant, List<String>> map : mapParticipantAndApis.entrySet()) {
            Assert.assertEquals(map.getKey(), participantMock);
            List<ApiResource> product_service = participantMock.getApiResources().stream().filter(apiResource -> "products-services".equals(apiResource.getApiFamilyType())).collect(Collectors.toList());
            Assert.assertEquals(map.getValue(), product_service.get(0).getApiEndpoint());
        }
    }
}
