package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Application;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.ApplicationEntityFixture;
import com.getdata.fixtures.resource.ApplicationFixture;
import com.getdata.fixtures.resource.InterestRateEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ApplicationToApplicationEntityMapperTest {

    @InjectMocks
    private ApplicationToApplicationEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_application_mapper_When_call_convert_to_application_entity_Then_return_application_entity() {

        final Application applicationMock = Fixture.from(Application.class).gimme(ApplicationFixture.VALID);
        final InterestRateEntity interestRateEntityMock = Fixture.from(InterestRateEntity.class).gimme(InterestRateEntityFixture.VALID);
        final ApplicationEntity applicationEntityMock = Fixture.from(ApplicationEntity.class).gimme(ApplicationEntityFixture.VALID);

        final ApplicationEntity applicationEntity = mapper.convert(applicationMock, interestRateEntityMock);

        assertThat(applicationEntity).isEqualTo(applicationEntityMock);
    }
}
