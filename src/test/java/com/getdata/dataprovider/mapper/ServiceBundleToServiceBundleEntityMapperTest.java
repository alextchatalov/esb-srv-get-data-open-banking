package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ServiceBundleEntityFixture;
import com.getdata.fixtures.resource.ServiceBundleFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ServiceBundleToServiceBundleEntityMapperTest {

    @InjectMocks
    private ServiceBundleToServiceBundleEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_service_bundler_mapper_When_call_convert_to_service_bundler_entity_Then_return_service_bundle_entity() {

        final ServiceBundleEntity serviceBundleEntityMock = Fixture.from(ServiceBundleEntity.class).gimme(ServiceBundleEntityFixture.VALID);
        final ServiceBundle serviceBundleMock = Fixture.from(ServiceBundle.class).gimme(ServiceBundleFixture.VALID);

        final ServiceBundleEntity serviceBundleEntity = mapper.convert(serviceBundleMock);

        assertThat(serviceBundleEntity).isEqualTo(serviceBundleEntityMock);
    }
}
