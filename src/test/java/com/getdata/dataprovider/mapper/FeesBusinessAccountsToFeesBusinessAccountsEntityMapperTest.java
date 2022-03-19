package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BusinessAccountEntityFixture;
import com.getdata.fixtures.resource.FeesBusinessAccountsEntityFixture;
import com.getdata.fixtures.resource.FeesBusinessAccountsFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class FeesBusinessAccountsToFeesBusinessAccountsEntityMapperTest {

    @InjectMocks
    private FeesBusinessAccountsToFeesBusinessAccountsEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_feesBusiness_accounts_mapper_When_call_convert_to_feesBusiness_accounts_entity_Then_return_feesBusiness_accounts_entity() {

        final BusinessAccountEntity businessAccountEntityMock = Fixture.from(BusinessAccountEntity.class).gimme(BusinessAccountEntityFixture.VALID);
        final FeesBusinessAccounts feesBusinessAccountsMock = Fixture.from(FeesBusinessAccounts.class).gimme(FeesBusinessAccountsFixture.VALID);
        final FeesBusinessAccountsEntity feesBusinessAccountsEntityMock = Fixture.from(FeesBusinessAccountsEntity.class).gimme(FeesBusinessAccountsEntityFixture.VALID);

        final FeesBusinessAccountsEntity feesBusinessAccountsEntity = mapper.convert(feesBusinessAccountsMock, businessAccountEntityMock);

        assertThat(feesBusinessAccountsEntity).isEqualTo(feesBusinessAccountsEntityMock);
    }
}
