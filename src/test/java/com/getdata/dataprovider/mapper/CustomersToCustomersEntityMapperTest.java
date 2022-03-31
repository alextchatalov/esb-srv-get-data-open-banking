package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Customers;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.CustomersEntityFixture;
import com.getdata.fixtures.resource.CustomersFixture;
import com.getdata.fixtures.resource.PriceEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CustomersToCustomersEntityMapperTest {

    @InjectMocks
    private CustomersToCustomersEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_customers_mapper_When_call_convert_to_customers_entity_Then_return_customers_entity() {

        final PriceEntity priceEntityMock = Fixture.from(PriceEntity.class).gimme(PriceEntityFixture.VALID);
        final Customers customersMock = Fixture.from(Customers.class).gimme(CustomersFixture.VALID);
        final CustomersEntity customersEntityMock = Fixture.from(CustomersEntity.class).gimme(CustomersEntityFixture.VALID);

        final CustomersEntity customersEntity = mapper.convertWithPrice(customersMock, priceEntityMock);

        assertThat(customersEntity).isEqualTo(customersEntityMock);
    }
}
