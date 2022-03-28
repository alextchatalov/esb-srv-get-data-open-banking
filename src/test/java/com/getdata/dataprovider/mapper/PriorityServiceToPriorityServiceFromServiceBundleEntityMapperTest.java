package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalAccountsEntityFixture;
import com.getdata.fixtures.resource.PriorityServiceEntityFixture;
import com.getdata.fixtures.resource.PriorityServiceFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PriorityServiceToPriorityServiceFromServiceBundleEntityMapperTest {

    @InjectMocks
    private PriorityServiceToPriorityServiceEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_priority_service_mapper_When_call_convert_to_priority_service_entity_Then_return_priority_service_entity() {

        final FeesPersonalAccountsEntity feesPersonalAccountsEntityMock = Fixture.from(FeesPersonalAccountsEntity.class).gimme(FeesPersonalAccountsEntityFixture.VALID);
        final PriorityServiceEntity priorityServiceEntityMock = Fixture.from(PriorityServiceEntity.class).gimme(PriorityServiceEntityFixture.VALID);
        final PriorityService priorityServiceMock = Fixture.from(PriorityService.class).gimme(PriorityServiceFixture.VALID);

        final PriorityServiceEntity priorityServiceEntity = mapper.convert(priorityServiceMock, feesPersonalAccountsEntityMock);

        assertThat(priorityServiceEntity).isEqualTo(priorityServiceEntityMock);
    }
}
