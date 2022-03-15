package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Minimum;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.MinimumEntityFixture;
import com.getdata.fixtures.resource.MinimumFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class MinimumToMinimumEntityMapperTest {

    @InjectMocks
    private MinimumToMinimumEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_minimum_mapper_When_call_convert_to_minimum_entity_Then_return_minimum_entity() {

        final MinimumEntity minimumEntityMock = Fixture.from(MinimumEntity.class).gimme(MinimumEntityFixture.VALID);
        final Minimum minimumMock = Fixture.from(Minimum.class).gimme(MinimumFixture.VALID);

        final MinimumEntity minimumEntity = mapper.convert(minimumMock);

        assertThat(minimumEntity).isEqualTo(minimumEntityMock);
    }
}
