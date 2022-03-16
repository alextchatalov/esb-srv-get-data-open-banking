package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalAccountsEntityFixture;
import com.getdata.fixtures.resource.FeesPersonalAccountsFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class FeesPersonalAccountsToFeesPersonalAccountsEntityMapperTest {

    @InjectMocks
    private FeesPersonalAccountsToFeesPersonalAccountsEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_fees_mapper_When_call_convert_to_fees_entity_Then_return_fees_entity() {

        final FeesPersonalAccounts feesPersonalAccountsMock = Fixture.from(FeesPersonalAccounts.class).gimme(FeesPersonalAccountsFixture.VALID);
        final FeesPersonalAccountsEntity feesPersonalAccountsEntityMock = Fixture.from(FeesPersonalAccountsEntity.class).gimme(FeesPersonalAccountsEntityFixture.VALID);

        final FeesPersonalAccountsEntity feesPersonalAccountsEntity = mapper.convert(feesPersonalAccountsMock);

        assertThat(feesPersonalAccountsEntity).isEqualTo(feesPersonalAccountsEntityMock);
    }
}
