package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.Company;
import com.getdata.core.model.PersonalAccount;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BusinessAccountEntityFixture;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import com.getdata.fixtures.resource.CompanyFixture;
import com.getdata.fixtures.resource.PersonalAccountEntityFixture;
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
class CompanyToCompanyEntityMapperTest {

    @InjectMocks
    private CompanyToCompanyEntityMapper mapper;

    @Mock
    PersonalAccountsToPersonalAccountEntityMapper personalAccountsToPersonalAccountEntityMapper;

    @Mock
    BusinessAccountToBusinessAccountEntityMapper businessAccountToBusinessAccountEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_company_mapper_When_call_convert_to_company_entity_Then_return_company_entity() {

        final Company companyMock = Fixture.from(Company.class).gimme(CompanyFixture.VALID);
        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final PersonalAccountEntity personalEntityMock = Fixture.from(PersonalAccountEntity.class).gimme(PersonalAccountEntityFixture.VALID);
        final BusinessAccountEntity businessEntityMock = Fixture.from(BusinessAccountEntity.class).gimme(BusinessAccountEntityFixture.VALID);

        when(personalAccountsToPersonalAccountEntityMapper.convert(any(PersonalAccount.class))).thenReturn(personalEntityMock);
        when(businessAccountToBusinessAccountEntityMapper.convert(any(BusinessAccount.class))).thenReturn(businessEntityMock);

        final CompanyEntity companyEntity = mapper.convert(companyMock);

        assertThat(companyEntity).isEqualTo(companyEntityMock);
    }
}
