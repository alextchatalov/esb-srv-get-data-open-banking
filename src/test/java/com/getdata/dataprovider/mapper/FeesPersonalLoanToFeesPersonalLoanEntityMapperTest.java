package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesPersonalLoan;
import com.getdata.dataprovider.entity.FeesPersonalLoanEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalLoanEntityFixture;
import com.getdata.fixtures.resource.FeesPersonalLoanFixture;
import com.getdata.fixtures.resource.PersonalLoanEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class FeesPersonalLoanToFeesPersonalLoanEntityMapperTest {

    @InjectMocks
    private FeesPersonalLoanToFeesPersonalLoanEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_fees_personal_loan_mapper_When_call_convert_to_fee_personal_loan_entity_Then_return_fee_personal_loa_entity() {

        final FeesPersonalLoan feesPersonalLoanMock = Fixture.from(FeesPersonalLoan.class).gimme(FeesPersonalLoanFixture.VALID);
        final PersonalLoanEntity personalLoanEntityMock = Fixture.from(PersonalLoanEntity.class).gimme(PersonalLoanEntityFixture.VALID);
        final FeesPersonalLoanEntity FeesPersonalLoanEntityMock = Fixture.from(FeesPersonalLoanEntity.class).gimme(FeesPersonalLoanEntityFixture.VALID);

        final FeesPersonalLoanEntity feesPersonalLoanEntity = mapper.convert(feesPersonalLoanMock, personalLoanEntityMock);

        assertThat(feesPersonalLoanEntity).isEqualTo(FeesPersonalLoanEntityMock);
    }
}
