package com.getdata.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Category;
import com.getdata.core.model.Participant;
import com.getdata.core.model.Request;
import com.getdata.fixtures.resource.ParticipantFixture;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FindUrlsProductsAndServicesUserCaseTest {

    @InjectMocks
    FindUrlsProductsAndServicesUserCase findUrlsProductsAndServicesUserCase;

    @Mock
    FindProductsAndServicesActiveUserCase findProductsAndServicesActiveUserCase;

    @Test
    void given_non_param_When_find_apis_products_and_serveces_then_return_list_of_apis_with_serveces() {
        final Participant participantMock = Fixture.from(Participant.class).gimme(ParticipantFixture.VALID);
        String apiMocked = "https://api.caixa.gov.br/open-banking/products-services/v1/personal-financings";
        Map<Participant, List<String>> listOfApisWithOutEntryPointMocked = new HashMap<>();
        listOfApisWithOutEntryPointMocked.put(participantMock, Arrays.asList(apiMocked));
        List<Request> apisExpected = new ArrayList<>();
        Request requestMok = Request.builder()
                .participant(participantMock)
                .category(Category.PERSONAL_ACCOUNTS)
                .url(apiMocked)
                .version("v1")
                .lastRequest(LocalDateTime.now()).build();
        apisExpected.add(requestMok);

        when(findProductsAndServicesActiveUserCase.execute()).thenReturn(listOfApisWithOutEntryPointMocked);
        List<Request> apis = findUrlsProductsAndServicesUserCase.execute();
        Assert.assertEquals(apisExpected.get(0).getUrl(), apis.get(0).getUrl());
    }
}
