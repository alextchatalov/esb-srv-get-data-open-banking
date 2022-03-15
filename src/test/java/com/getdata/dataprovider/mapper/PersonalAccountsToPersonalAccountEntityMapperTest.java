package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.FeesPersonalAccountsEntityFixture;
import com.getdata.fixtures.resource.IncomeRateEntityFixture;
import com.getdata.fixtures.resource.PersonalAccountEntityFixture;
import com.getdata.fixtures.resource.PersonalAccountFixture;
import com.getdata.fixtures.resource.ServiceBundleEntityFixture;
import com.getdata.fixtures.resource.TermsConditionsEntityFixture;
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
class PersonalAccountsToPersonalAccountEntityMapperTest {

    @InjectMocks
    private PersonalAccountsToPersonalAccountEntityMapper mapper;

    @Mock
    FeesPersonalAccountsToFeesPersonalAccountsEntityMapper feesPersonalAccountsToFeesPersonalAccountsEntityMapper;

    @Mock
    ServiceBundleToServiceBundleEntityMapper serviceBundleToServiceBundleEntityMapper;

    @Mock
    TermsConditionsToTermsConditionsEntityMapper termsConditionsToTermsConditionsEntityMapper;

    @Mock
    IncomeRateToIncomeRateEntityMapper incomeRateToIncomeRateEntityMapper;


    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_a_personal_account_mapper_When_call_convert_to_personal_account_entity_Then_return_personal_account_entity() {

        final ServiceBundleEntity serviceBundleEntityMock = Fixture.from(ServiceBundleEntity.class).gimme(ServiceBundleEntityFixture.VALID);
        final FeesPersonalAccountsEntity feesPersonalAccountsEntityMock = Fixture.from(FeesPersonalAccountsEntity.class).gimme(FeesPersonalAccountsEntityFixture.VALID);
        final TermsConditionsEntity termsConditionsEntityMock = Fixture.from(TermsConditionsEntity.class).gimme(TermsConditionsEntityFixture.VALID);
        final IncomeRateEntity incomeRateEntityMock = Fixture.from(IncomeRateEntity.class).gimme(IncomeRateEntityFixture.VALID);

        final PersonalAccount personalAccountMock = Fixture.from(PersonalAccount.class).gimme(PersonalAccountFixture.VALID);
        final PersonalAccountEntity personalAccountEntityMock = Fixture.from(PersonalAccountEntity.class).gimme(PersonalAccountEntityFixture.VALID);

        when(serviceBundleToServiceBundleEntityMapper.convert(any(ServiceBundle.class))).thenReturn(serviceBundleEntityMock);
        when(feesPersonalAccountsToFeesPersonalAccountsEntityMapper.convert(any(FeesPersonalAccounts.class))).thenReturn(feesPersonalAccountsEntityMock);
        when(termsConditionsToTermsConditionsEntityMapper.convert(any(TermsConditions.class))).thenReturn(termsConditionsEntityMock);
        when(incomeRateToIncomeRateEntityMapper.convert(any(IncomeRate.class))).thenReturn(incomeRateEntityMock);


        final PersonalAccountEntity personalAccountEntity = mapper.convert(personalAccountMock);

        assertThat(personalAccountEntity).isEqualTo(personalAccountEntityMock);
    }
}
