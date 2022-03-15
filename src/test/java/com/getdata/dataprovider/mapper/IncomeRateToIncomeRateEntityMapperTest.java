package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.IncomeRate;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.IncomeRateEntityFixture;
import com.getdata.fixtures.resource.IncomeRateFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class IncomeRateToIncomeRateEntityMapperTest {

    @InjectMocks
    private IncomeRateToIncomeRateEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_incomeRate_mapper_When_call_convert_to_incomeRate_entity_Then_return_incomeRate_entity() {

        final IncomeRate incomeRateMock = Fixture.from(IncomeRate.class).gimme(IncomeRateFixture.VALID);
        final IncomeRateEntity incomeRateEntityMock = Fixture.from(IncomeRateEntity.class).gimme(IncomeRateEntityFixture.VALID);

        final IncomeRateEntity incomeRateEntity = mapper.convert(incomeRateMock);

        assertThat(incomeRateEntity).isEqualTo(incomeRateEntityMock);
    }
}
