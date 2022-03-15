package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Company;
import com.getdata.core.model.Data;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import com.getdata.fixtures.resource.DataEntityFixture;
import com.getdata.fixtures.resource.DataFixture;
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
class DataToDataEntityMapperTest {

    @InjectMocks
    private DataToDataEntityMapper mapper;

    @Mock
    CompanyToCompanyEntityMapper companyToCompanyEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_data_mapper_When_call_convert_to_data_entity_Then_return_data_entity() {

        final Data dataMock = Fixture.from(Data.class).gimme(DataFixture.VALID);
        final DataEntity dataEntityMock = Fixture.from(DataEntity.class).gimme(DataEntityFixture.VALID);
        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);

        when(companyToCompanyEntityMapper.convert(any(Company.class))).thenReturn(companyEntityMock);

        final DataEntity dataEntity = mapper.convert(dataMock);

        assertThat(dataEntity).isEqualTo(dataEntityMock);
    }
}
