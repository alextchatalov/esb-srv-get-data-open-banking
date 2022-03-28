package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.OtherService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalAccountsEntityFixture;
import com.getdata.fixtures.resource.OtherServiceEntityFixture;
import com.getdata.fixtures.resource.OtherServiceFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class OtherServiceToOtherServiceEntityMapperTest {

    @InjectMocks
    private OtherServiceToOtherServiceEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_other_service_mapper_When_call_convert_to_other_service_entity_Then_return_other_service_entity() {

        final FeesPersonalAccountsEntity feesPersonalAccountsEntityMock = Fixture.from(FeesPersonalAccountsEntity.class).gimme(FeesPersonalAccountsEntityFixture.VALID);
        final OtherService otherServiceMock = Fixture.from(OtherService.class).gimme(OtherServiceFixture.VALID);
        final OtherServiceEntity otherServiceEntityMock = Fixture.from(OtherServiceEntity.class).gimme(OtherServiceEntityFixture.VALID);

        final OtherServiceEntity otherServiceEntity = mapper.convert(otherServiceMock, feesPersonalAccountsEntityMock);

        assertThat(otherServiceEntity).isEqualTo(otherServiceEntityMock);
    }
}
