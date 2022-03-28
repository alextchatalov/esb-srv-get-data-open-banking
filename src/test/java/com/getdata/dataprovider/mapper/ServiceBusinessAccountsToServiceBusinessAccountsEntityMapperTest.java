package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesBusinessAccountsEntityFixture;
import com.getdata.fixtures.resource.ServiceBusinessAccountsEntityFixture;
import com.getdata.fixtures.resource.ServiceBusinessAccountsFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ServiceBusinessAccountsToServiceBusinessAccountsEntityMapperTest {

    @InjectMocks
    private ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_service_business_accounts_mapper_When_call_convert_to_service_business_accounts_entity_Then_return_service_business_accounts_entity() {

        final FeesBusinessAccountsEntity feesBusinessAccountsEntityMock = Fixture.from(FeesBusinessAccountsEntity.class).gimme(FeesBusinessAccountsEntityFixture.VALID);
        final ServiceBusinessAccounts serviceBusinessAccountsMock = Fixture.from(ServiceBusinessAccounts.class).gimme(ServiceBusinessAccountsFixture.VALID);
        final ServiceBusinessAccountsEntity serviceBusinessAccountsEntityAccountsMock = Fixture.from(ServiceBusinessAccountsEntity.class).gimme(ServiceBusinessAccountsEntityFixture.VALID);

        final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity = mapper.convert(serviceBusinessAccountsMock, feesBusinessAccountsEntityMock);

        assertThat(serviceBusinessAccountsEntity).isEqualTo(serviceBusinessAccountsEntityAccountsMock);
    }
}
