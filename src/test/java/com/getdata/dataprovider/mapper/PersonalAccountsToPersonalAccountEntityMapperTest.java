package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.PersonalAccount;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.CompanyEntityFixture;
import com.getdata.fixtures.resource.PersonalAccountEntityFixture;
import com.getdata.fixtures.resource.PersonalAccountFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonalAccountsToPersonalAccountEntityMapperTest {

    @InjectMocks
    private PersonalAccountsToPersonalAccountEntityMapper mapper;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_personal_account_mapper_When_call_convert_to_personal_account_entity_Then_return_personal_account_entity() {

        final CompanyEntity companyEntityMock = Fixture.from(CompanyEntity.class).gimme(CompanyEntityFixture.VALID);
        final PersonalAccount personalAccountMock = Fixture.from(PersonalAccount.class).gimme(PersonalAccountFixture.VALID);
        final PersonalAccountEntity personalAccountEntityMock = Fixture.from(PersonalAccountEntity.class).gimme(PersonalAccountEntityFixture.VALID);

        final PersonalAccountEntity personalAccountEntity = mapper.convert(personalAccountMock, companyEntityMock);

        assertThat(personalAccountEntity).isEqualTo(personalAccountEntityMock);
    }
}
