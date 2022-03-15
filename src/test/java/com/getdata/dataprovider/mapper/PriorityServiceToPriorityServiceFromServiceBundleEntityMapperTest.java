package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.MaximumEntityFixture;
import com.getdata.fixtures.resource.MinimumEntityFixture;
import com.getdata.fixtures.resource.PriceEntityFixture;
import com.getdata.fixtures.resource.PriorityServiceEntityFixture;
import com.getdata.fixtures.resource.PriorityServiceFixture;
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
class PriorityServiceToPriorityServiceFromServiceBundleEntityMapperTest {

    @InjectMocks
    private PriorityServiceToPriorityServiceEntityMapper mapper;

    @Mock
    PriceToPriceEntityMapper priceToPriceEntityMapper;

    @Mock
    MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;

    @Mock
    MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;


    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_priority_service_mapper_When_call_convert_to_priority_service_entity_Then_return_priority_service_entity() {

        final PriceEntity priceEntityMock = Fixture.from(PriceEntity.class).gimme(PriceEntityFixture.VALID);
        final MinimumEntity minimumEntityMock = Fixture.from(MinimumEntity.class).gimme(MinimumEntityFixture.VALID);
        final MaximumEntity maximumEntityMock = Fixture.from(MaximumEntity.class).gimme(MaximumEntityFixture.VALID);

        final PriorityServiceEntity priorityServiceEntityMock = Fixture.from(PriorityServiceEntity.class).gimme(PriorityServiceEntityFixture.VALID);
        final PriorityService priorityServiceMock = Fixture.from(PriorityService.class).gimme(PriorityServiceFixture.VALID);


        when(priceToPriceEntityMapper.convert(any(Price.class))).thenReturn(priceEntityMock);
        when(minimumToMinimumEntityMapper.convert(any(Minimum.class))).thenReturn(minimumEntityMock);
        when(maximumToMaximumEntityMapper.convert(any(Maximum.class))).thenReturn(maximumEntityMock);

        final PriorityServiceEntity priorityServiceEntity = mapper.convert(priorityServiceMock);

        assertThat(priorityServiceEntity).isEqualTo(priorityServiceEntityMock);
    }
}
