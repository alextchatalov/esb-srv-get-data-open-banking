package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesBusinessAccountsEntityFixture;
import com.getdata.fixtures.resource.FeesBusinessAccountsFixture;
import com.getdata.fixtures.resource.ServiceBusinessAccountsEntityFixture;
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
class FeesBusinessAccountsToFeesBusinessAccountsEntityMapperTest {

    @InjectMocks
    private FeesBusinessAccountsToFeesBusinessAccountsEntityMapper mapper;

    @Mock
    ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper serviceBusinessAccountsToServiceBusinessAccountsEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_feesBusiness_accounts_mapper_When_call_convert_to_feesBusiness_accounts_entity_Then_return_feesBusiness_accounts_entity() {

        final ServiceBusinessAccountsEntity serviceBusinessAccountsEntityMock = Fixture.from(ServiceBusinessAccountsEntity.class).gimme(ServiceBusinessAccountsEntityFixture.VALID);

        final FeesBusinessAccounts feesBusinessAccountsMock = Fixture.from(FeesBusinessAccounts.class).gimme(FeesBusinessAccountsFixture.VALID);
        final FeesBusinessAccountsEntity feesBusinessAccountsEntityMock = Fixture.from(FeesBusinessAccountsEntity.class).gimme(FeesBusinessAccountsEntityFixture.VALID);

        when(serviceBusinessAccountsToServiceBusinessAccountsEntityMapper.convert(any(ServiceBusinessAccounts.class))).thenReturn(serviceBusinessAccountsEntityMock);

        final FeesBusinessAccountsEntity feesBusinessAccountsEntity = mapper.convert(feesBusinessAccountsMock);

        assertThat(feesBusinessAccountsEntity).isEqualTo(feesBusinessAccountsEntityMock);
    }
}
