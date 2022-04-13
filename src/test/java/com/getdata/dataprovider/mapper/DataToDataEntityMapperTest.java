package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Data;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.DataEntityFixture;
import com.getdata.fixtures.resource.DataFixture;
import com.getdata.fixtures.resource.ParticipantEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class DataToDataEntityMapperTest {

    @InjectMocks
    private DataToDataEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_data_mapper_When_call_convert_to_data_entity_Then_return_data_entity() {

        final Data dataMock = Fixture.from(Data.class).gimme(DataFixture.VALID);
        final DataEntity dataEntityMock = Fixture.from(DataEntity.class).gimme(DataEntityFixture.VALID);
        final ParticipantEntity participantEntityMock = Fixture.from(ParticipantEntity.class).gimme(ParticipantEntityFixture.VALID);

        final DataEntity dataEntity = mapper.convert(dataMock, participantEntityMock);

        assertThat(dataEntity).isEqualTo(dataEntityMock);
    }
}
