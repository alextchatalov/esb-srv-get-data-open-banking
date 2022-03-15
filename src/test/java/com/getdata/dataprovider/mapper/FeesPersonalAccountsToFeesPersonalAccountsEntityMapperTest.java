package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalAccountsEntityFixture;
import com.getdata.fixtures.resource.FeesPersonalAccountsFixture;
import com.getdata.fixtures.resource.OtherServiceEntityFixture;
import com.getdata.fixtures.resource.PriorityServiceEntityFixture;
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
class FeesPersonalAccountsToFeesPersonalAccountsEntityMapperTest {

    @InjectMocks
    private FeesPersonalAccountsToFeesPersonalAccountsEntityMapper mapper;

    @Mock
    PriorityServiceToPriorityServiceEntityMapper priorityServiceToPriorityServiceEntityMapper;

    @Mock
    OtherServiceToOtherServiceEntityMapper otherServiceToOtherServiceEntityMapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_fees_mapper_When_call_convert_to_fees_entity_Then_return_fees_entity() {

        final FeesPersonalAccounts feesPersonalAccountsMock = Fixture.from(FeesPersonalAccounts.class).gimme(FeesPersonalAccountsFixture.VALID);
        final FeesPersonalAccountsEntity feesPersonalAccountsEntityMock = Fixture.from(FeesPersonalAccountsEntity.class).gimme(FeesPersonalAccountsEntityFixture.VALID);
        final PriorityServiceEntity priorityServiceEntityMock = Fixture.from(PriorityServiceEntity.class).gimme(PriorityServiceEntityFixture.VALID);
        final OtherServiceEntity otherServiceEntityMock = Fixture.from(OtherServiceEntity.class).gimme(OtherServiceEntityFixture.VALID);

        when(priorityServiceToPriorityServiceEntityMapper.convert(any(PriorityService.class))).thenReturn(priorityServiceEntityMock);
        when(otherServiceToOtherServiceEntityMapper.convert(any(OtherService.class))).thenReturn(otherServiceEntityMock);

        final FeesPersonalAccountsEntity feesPersonalAccountsEntity = mapper.convert(feesPersonalAccountsMock);

        assertThat(feesPersonalAccountsEntity).isEqualTo(feesPersonalAccountsEntityMock);
    }
}
