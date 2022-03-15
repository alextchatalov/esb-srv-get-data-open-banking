package com.getdata.dataprovider.gateway;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Data;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.mapper.DataToDataEntityMapper;
import com.getdata.dataprovider.repository.DataRepository;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.DataEntityFixture;
import com.getdata.fixtures.resource.DataFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SaveResponseGatewayTest {

    @InjectMocks
    SaveResponseGateway saveResponseGateway;

    @Mock
    DataRepository repository;

    @Mock
    DataToDataEntityMapper dataToDataEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_valid_response_When_save_Then_return_response_saved() {

        final DataEntity dataEntityMock = Fixture.from(DataEntity.class).gimme(DataEntityFixture.VALID);
        final Data dataMock = Fixture.from(Data.class).gimme(DataFixture.VALID);

        when(dataToDataEntityMapper.convert(any(Data.class))).thenReturn(dataEntityMock);
        when(repository.save(any(DataEntity.class))).thenReturn(dataEntityMock);

        saveResponseGateway.execute(dataMock);
        verify(repository, times(1)).save(any(DataEntity.class));
    }
}
