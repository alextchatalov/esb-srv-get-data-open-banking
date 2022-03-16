package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Company;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BrandEntityFixture;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import com.getdata.fixtures.resource.CompanyFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CompanyToCompanyEntityMapperTest {

    @InjectMocks
    private CompanyToCompanyEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_company_mapper_When_call_convert_to_company_entity_Then_return_company_entity() {

        final Company companyMock = Fixture.from(Company.class).gimme(CompanyFixture.VALID);
        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final BrandEntity brandEntityMock = Fixture.from(BrandEntity.class).gimme(BrandEntityFixture.VALID);

        final CompanyEntity companyEntity = mapper.convert(companyMock, brandEntityMock);

        assertThat(companyEntity).isEqualTo(companyEntityMock);
    }
}
