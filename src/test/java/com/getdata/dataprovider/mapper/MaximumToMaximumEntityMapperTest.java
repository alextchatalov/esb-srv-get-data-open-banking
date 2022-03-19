package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Maximum;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.MaximumEntityFixture;
import com.getdata.fixtures.resource.MaximumFixture;
import com.getdata.fixtures.resource.PriorityServiceEntityFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class MaximumToMaximumEntityMapperTest {

    @InjectMocks
    private MaximumToMaximumEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_maximum_mapper_When_call_convert_to_maximum_entity_Then_return_maximum_entity() {

        final PriorityServiceEntity priorityServiceEntityMock = Fixture.from(PriorityServiceEntity.class).gimme(PriorityServiceEntityFixture.VALID);
        final Maximum maximumMock = Fixture.from(Maximum.class).gimme(MaximumFixture.VALID);
        final MaximumEntity maximumEntityMock = Fixture.from(MaximumEntity.class).gimme(MaximumEntityFixture.VALID);

        final MaximumEntity maximumEntity = mapper.convertWithPriorityService(maximumMock, priorityServiceEntityMock);

        assertThat(maximumEntity).isEqualTo(maximumEntityMock);
    }
}
