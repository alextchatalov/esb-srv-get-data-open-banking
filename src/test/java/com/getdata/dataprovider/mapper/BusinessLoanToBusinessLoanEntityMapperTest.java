package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.BusinessLoan;
import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BusinessLoanEntityFixture;
import com.getdata.fixtures.resource.BusinessLoanFixture;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class BusinessLoanToBusinessLoanEntityMapperTest {

    @InjectMocks
    private BusinessLoanToBusinessLoanEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_business_loan_mapper_When_call_convert_to_business_loan_entity_Then_return_business_loan_entity() {

        final BusinessLoan businessLoanMock = Fixture.from(BusinessLoan.class).gimme(BusinessLoanFixture.VALID);
        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final BusinessLoanEntity businessLoanEntityMock = Fixture.from(BusinessLoanEntity.class).gimme(BusinessLoanEntityFixture.VALID);

        final BusinessLoanEntity businessLoanEntity = mapper.convert(businessLoanMock, companyEntityMock);

        assertThat(businessLoanEntity).isEqualTo(businessLoanEntityMock);
    }
}
