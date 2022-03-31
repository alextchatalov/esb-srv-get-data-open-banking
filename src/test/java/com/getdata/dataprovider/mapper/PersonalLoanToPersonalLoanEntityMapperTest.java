package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.PersonalLoan;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import com.getdata.fixtures.resource.PersonalLoanEntityFixture;
import com.getdata.fixtures.resource.PersonalLoanFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonalLoanToPersonalLoanEntityMapperTest {

    @InjectMocks
    private PersonalLoanToPersonalLoanEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_personal_loan_mapper_When_call_convert_to_personal_loan_entity_Then_return_personal_loan_entity() {

        final PersonalLoan personalLoanMock = Fixture.from(PersonalLoan.class).gimme(PersonalLoanFixture.VALID);
        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final PersonalLoanEntity personalLoanEntityMock = Fixture.from(PersonalLoanEntity.class).gimme(PersonalLoanEntityFixture.VALID);

        final PersonalLoanEntity personalLoanEntity = mapper.convert(personalLoanMock, companyEntityMock);

        assertThat(personalLoanEntity).isEqualTo(personalLoanEntityMock);
    }
}
