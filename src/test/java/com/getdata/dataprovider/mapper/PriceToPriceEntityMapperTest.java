package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.PriceEntityFixture;
import com.getdata.fixtures.resource.PriceFixture;
import com.getdata.fixtures.resource.PriorityServiceEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PriceToPriceEntityMapperTest {

    @InjectMocks
    private PriceToPriceEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_price_mapper_When_call_convert_to_price_entity_Then_return_price_entity() {

        final PriorityServiceEntity priorityServiceEntityMock = Fixture.from(PriorityServiceEntity.class).gimme(PriorityServiceEntityFixture.VALID);
        final Price priceMock = Fixture.from(Price.class).gimme(PriceFixture.VALID);
        final PriceEntity priceEntityMock = Fixture.from(PriceEntity.class).gimme(PriceEntityFixture.VALID);

        final PriceEntity priceEntity = mapper.convertWithPriorityService(priceMock, priorityServiceEntityMock);

        assertThat(priceEntity).isEqualTo(priceEntityMock);
    }
}
