package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.TermsConditionsEntityFixture;
import com.getdata.fixtures.resource.TermsConditionsFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class TermsConditionsToTermsConditionsEntityMapperTest {

    @InjectMocks
    private TermsConditionsToTermsConditionsEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_terms_conditions_mapper_When_call_convert_to_terms_conditions_entity_Then_return_terms_conditions_entity() {

        final TermsConditions termsConditionsMock = Fixture.from(TermsConditions.class).gimme(TermsConditionsFixture.VALID);
        final TermsConditionsEntity termsConditionsEntityMock = Fixture.from(TermsConditionsEntity.class).gimme(TermsConditionsEntityFixture.VALID);

        final TermsConditionsEntity termsConditionsEntity = mapper.convert(termsConditionsMock);

        assertThat(termsConditionsEntity).isEqualTo(termsConditionsEntityMock);
    }
}
