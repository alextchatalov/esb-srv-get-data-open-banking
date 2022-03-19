package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ServiceBundleEntityFixture;
import com.getdata.fixtures.resource.ServiceFromServiceBundleEntityFixture;
import com.getdata.fixtures.resource.ServiceFromServiceBundleFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ServiceFromServiceBundleToServiceFromServiceBundleEntityMapperTest {

    @InjectMocks
    private ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_service_mapper_When_call_convert_to_service_entity_Then_return_service_entity() {

        final ServiceBundleEntity serviceBundleEntityMock = Fixture.from(ServiceBundleEntity.class).gimme(ServiceBundleEntityFixture.VALID);
        final ServiceFromServiceBundle serviceFromServiceBundleMock = Fixture.from(ServiceFromServiceBundle.class).gimme(ServiceFromServiceBundleFixture.VALID);
        final ServiceFromServiceBundleEntity serviceFromServiceBundleEntityMock = Fixture.from(ServiceFromServiceBundleEntity.class).gimme(ServiceFromServiceBundleEntityFixture.VALID);

        final ServiceFromServiceBundleEntity serviceFromServiceBundleEntity = mapper.convert(serviceFromServiceBundleMock, serviceBundleEntityMock);

        assertThat(serviceFromServiceBundleEntity).isEqualTo(serviceFromServiceBundleEntityMock);
    }
}
