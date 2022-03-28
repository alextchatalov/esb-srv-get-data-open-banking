package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.BusinessAccount;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BusinessAccountEntityFixture;
import com.getdata.fixtures.resource.BusinessAccountFixture;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class BusinessAccountToBusinessAccountEntityMapperTest {

    @InjectMocks
    private BusinessAccountToBusinessAccountEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_business_account_mapper_When_call_convert_to_business_account_entity_Then_return_business_account_entity() {

        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final BusinessAccount businessAccountMock = Fixture.from(BusinessAccount.class).gimme(BusinessAccountFixture.VALID);
        final BusinessAccountEntity businessAccountEntityMock = Fixture.from(BusinessAccountEntity.class).gimme(BusinessAccountEntityFixture.VALID);

        final BusinessAccountEntity businessAccountEntity = mapper.convert(businessAccountMock, companyEntityMock);

        assertThat(businessAccountEntity).isEqualTo(businessAccountEntityMock);
    }
}
