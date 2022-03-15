package com.getdata.dataprovider.mapper;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.BusinessAccountEntityFixture;
import com.getdata.fixtures.resource.BusinessAccountFixture;
import com.getdata.fixtures.resource.FeesBusinessAccountsEntityFixture;
import com.getdata.fixtures.resource.IncomeRateEntityFixture;
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
class BusinessAccountToBusinessAccountEntityMapperTest {

    @InjectMocks
    private BusinessAccountToBusinessAccountEntityMapper mapper;

    @Mock
    FeesBusinessAccountsToFeesBusinessAccountsEntityMapper feesBusinessAccountsToFeesBusinessAccountsEntityMapper;

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
    void given_a_business_account_mapper_When_call_convert_to_business_account_entity_Then_return_business_account_entity() {

        final ServiceBundleEntity serviceBundleEntityMock = Fixture.from(ServiceBundleEntity.class).gimme(ServiceBundleEntityFixture.VALID);
        final FeesBusinessAccountsEntity feesBusinessAccountEntityMock = Fixture.from(FeesBusinessAccountsEntity.class).gimme(FeesBusinessAccountsEntityFixture.VALID);
        final TermsConditionsEntity termsConditionsEntityMock = Fixture.from(TermsConditionsEntity.class).gimme(TermsConditionsEntityFixture.VALID);
        final IncomeRateEntity incomeRateEntityMock = Fixture.from(IncomeRateEntity.class).gimme(IncomeRateEntityFixture.VALID);

        final BusinessAccount businessAccountMock = Fixture.from(BusinessAccount.class).gimme(BusinessAccountFixture.VALID);
        final BusinessAccountEntity businessAccountEntityMock = Fixture.from(BusinessAccountEntity.class).gimme(BusinessAccountEntityFixture.VALID);

        when(serviceBundleToServiceBundleEntityMapper.convert(any(ServiceBundle.class))).thenReturn(serviceBundleEntityMock);
        when(feesBusinessAccountsToFeesBusinessAccountsEntityMapper.convert(any(FeesBusinessAccounts.class))).thenReturn(feesBusinessAccountEntityMock);
        when(termsConditionsToTermsConditionsEntityMapper.convert(any(TermsConditions.class))).thenReturn(termsConditionsEntityMock);
        when(incomeRateToIncomeRateEntityMapper.convert(any(IncomeRate.class))).thenReturn(incomeRateEntityMock);


        final BusinessAccountEntity businessAccountEntity = mapper.convert(businessAccountMock);

        assertThat(businessAccountEntity).isEqualTo(businessAccountEntityMock);
    }
}
