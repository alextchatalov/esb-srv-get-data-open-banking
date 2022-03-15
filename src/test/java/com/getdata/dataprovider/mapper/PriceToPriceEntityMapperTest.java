package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.CustomersEntityFixture;
import com.getdata.fixtures.resource.PriceEntityFixture;
import com.getdata.fixtures.resource.PriceFixture;
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
class PriceToPriceEntityMapperTest {

    @InjectMocks
    private PriceToPriceEntityMapper mapper;

    @Mock
    CustomersToCustomersEntityMapper customersToCustomersEntityMapper;


    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_price_mapper_When_call_convert_to_price_entity_Then_return_price_entity() {

        final CustomersEntity customersEntityMock = Fixture.from(CustomersEntity.class).gimme(CustomersEntityFixture.VALID);
        final Price priceMock = Fixture.from(Price.class).gimme(PriceFixture.VALID);
        final PriceEntity priceEntityMock = Fixture.from(PriceEntity.class).gimme(PriceEntityFixture.VALID);

        when(customersToCustomersEntityMapper.convert(any(Customers.class))).thenReturn(customersEntityMock);


        final PriceEntity priceEntity = mapper.convert(priceMock);

        assertThat(priceEntity).isEqualTo(priceEntityMock);
    }
}
