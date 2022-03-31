package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.InterestRate;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.InterestRateEntityFixture;
import com.getdata.fixtures.resource.InterestRateFixture;
import com.getdata.fixtures.resource.PersonalLoanEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class InterestRateToInterestRateEntityMapperTest {

    @InjectMocks
    private InterestRateToInterestRateEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_interest_rate_mapper_When_call_convert_to_interest_rate_entity_Then_return_interest_rate_entity() {

        final InterestRate interestRateMock = Fixture.from(InterestRate.class).gimme(InterestRateFixture.VALID);
        final PersonalLoanEntity personalLoanEntityMock = Fixture.from(PersonalLoanEntity.class).gimme(PersonalLoanEntityFixture.VALID);
        final InterestRateEntity interestRateEntityMock = Fixture.from(InterestRateEntity.class).gimme(InterestRateEntityFixture.VALID);

        final InterestRateEntity interestRateEntity = mapper.convert(interestRateMock, personalLoanEntityMock);

        assertThat(interestRateEntity).isEqualTo(interestRateEntityMock);
    }
}
