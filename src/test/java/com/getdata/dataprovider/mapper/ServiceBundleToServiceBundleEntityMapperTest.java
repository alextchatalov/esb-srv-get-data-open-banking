package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.MaximumEntityFixture;
import com.getdata.fixtures.resource.MinimumEntityFixture;
import com.getdata.fixtures.resource.PriceEntityFixture;
import com.getdata.fixtures.resource.ServiceBundleEntityFixture;
import com.getdata.fixtures.resource.ServiceBundleFixture;
import com.getdata.fixtures.resource.ServiceFromServiceBundleEntityFixture;
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
class ServiceBundleToServiceBundleEntityMapperTest {

    @InjectMocks
    private ServiceBundleToServiceBundleEntityMapper mapper;

    @Mock
    PriceToPriceEntityMapper priceToPriceEntityMapper;

    @Mock
    MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;

    @Mock
    MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;

    @Mock
    ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper serviceFromServiceBundleToServiceFromServiceBundleEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_service_bundler_mapper_When_call_convert_to_service_bundler_entity_Then_return_service_bundle_entity() {

        final PriceEntity priceEntityMock = Fixture.from(PriceEntity.class).gimme(PriceEntityFixture.VALID);
        final MinimumEntity minimumEntityMock = Fixture.from(MinimumEntity.class).gimme(MinimumEntityFixture.VALID);
        final MaximumEntity maximumEntityMock = Fixture.from(MaximumEntity.class).gimme(MaximumEntityFixture.VALID);
        final ServiceFromServiceBundleEntity serviceFromServiceBundleEntityMock = Fixture.from(ServiceFromServiceBundleEntity.class).gimme(ServiceFromServiceBundleEntityFixture.VALID);
        final ServiceBundleEntity serviceBundleEntityMock = Fixture.from(ServiceBundleEntity.class).gimme(ServiceBundleEntityFixture.VALID);
        final ServiceBundle serviceBundleMock = Fixture.from(ServiceBundle.class).gimme(ServiceBundleFixture.VALID);


        when(priceToPriceEntityMapper.convert(any(Price.class))).thenReturn(priceEntityMock);
        when(minimumToMinimumEntityMapper.convert(any(Minimum.class))).thenReturn(minimumEntityMock);
        when(maximumToMaximumEntityMapper.convert(any(Maximum.class))).thenReturn(maximumEntityMock);
        when(serviceFromServiceBundleToServiceFromServiceBundleEntityMapper.convert(any(ServiceFromServiceBundle.class))).thenReturn(serviceFromServiceBundleEntityMock);

        final ServiceBundleEntity serviceBundleEntity = mapper.convert(serviceBundleMock);

        assertThat(serviceBundleEntity).isEqualTo(serviceBundleEntityMock);
    }
}
