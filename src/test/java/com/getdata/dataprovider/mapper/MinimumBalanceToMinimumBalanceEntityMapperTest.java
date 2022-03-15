package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.MinimumBalance;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.MinimumBalanceEntityFixture;
import com.getdata.fixtures.resource.MinimumBalanceFixture;
import com.getdata.fixtures.resource.TermsConditionsEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class MinimumBalanceToMinimumBalanceEntityMapperTest {

    @InjectMocks
    private MinimumBalanceToMinimumBalanceEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_minimum_mapper_When_call_convert_to_minimum_entity_Then_return_minimum_entity() {

        final MinimumBalance minimumBalanceMock = Fixture.from(MinimumBalance.class).gimme(MinimumBalanceFixture.VALID);
        final MinimumBalanceEntity minimumBalanceEntityMock = Fixture.from(MinimumBalanceEntity.class).gimme(MinimumBalanceEntityFixture.VALID);
        final TermsConditionsEntity termsConditionsEntityMock = Fixture.from(TermsConditionsEntity.class).gimme(TermsConditionsEntityFixture.VALID);

        final MinimumBalanceEntity minimumBalanceEntity = mapper.convert(minimumBalanceMock, termsConditionsEntityMock);

        assertThat(minimumBalanceEntity).isEqualTo(minimumBalanceEntityMock);
    }
}
