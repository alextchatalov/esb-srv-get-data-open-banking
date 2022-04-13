package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Brand;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrandEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(BrandEntity.class).addTemplate(VALID, new Rule() {{
            add(BrandEntity.Fields.name, "test");
            add(BrandEntity.Fields.companies, Collections.singletonList(createCompany()));

        }});
    }

    private CompanyEntity createCompany() {

        return CompanyEntity.builder()
                .name("test")
                .cnpjNumber("1212424")
                .urlComplementaryList("test")
                .personalAccounts(Collections.singletonList(createPersonalAccount()))
                .build();
    }

    private PersonalAccountEntity createPersonalAccount() {

        final PersonalAccountEntity personalAccountEntity = PersonalAccountEntity.builder()
                .type("test")
                .fees(createFees())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .additionalInfo("test")
                .termsConditions(createTermsConditions())
                .incomeRate(Collections.singletonList(createIncomeRate()))
                .build();

        personalAccountEntity.setOpeningClosingChannels(createOpeningClosingChannels(personalAccountEntity));
        personalAccountEntity.setTransactionMethods(createTransactionMethods(personalAccountEntity));

        return personalAccountEntity;
    }

    private List<OpeningClosingChannelsEntity> createOpeningClosingChannels(final PersonalAccountEntity personalAccount) {

        return Arrays.asList(OpeningClosingChannelsEntity.builder()
                .channel("test")
                .personalAccount(personalAccount)
                .businessAccount(null)
                .build());
    }

    private List<TransactionMethodsEntity> createTransactionMethods(final PersonalAccountEntity personalAccount) {

        return Arrays.asList(TransactionMethodsEntity.builder()
                .method("test")
                .personalAccount(personalAccount)
                .businessAccount(null)
                .build());
    }

    private IncomeRateEntity createIncomeRate() {

        return IncomeRateEntity.builder()
                .savingAccount("123")
                .prepaidPaymentAccount("123")
                .build();
    }

    private TermsConditionsEntity createTermsConditions() {

        return TermsConditionsEntity.builder()
                .minimumBalance(createMinimumBalance())
                .elegibilityCriteriaInfo("test")
                .closingProcessInfo("1")
                .build();
    }

    private MinimumBalanceEntity createMinimumBalance() {
        return MinimumBalanceEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private ServiceBundleEntity createServiceBundles() {

        return ServiceBundleEntity.builder()
                .name("test")
                .services(Collections.singletonList(createService()))
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private ServiceFromServiceBundleEntity createService() {

        return ServiceFromServiceBundleEntity.builder()
                .code("123")
                .chargingTriggerInfo("test")
                .eventLimitQuantity("1")
                .freeEventQuantity("1")
                .build();
    }


    private FeesPersonalAccountsEntity createFees() {

        return FeesPersonalAccountsEntity.builder()
                .priorityServices(Collections.singletonList(createPriorityService()))
                .otherServices(Collections.singletonList(createOtherService()))
                .build();
    }

    private OtherServiceEntity createOtherService() {

        return OtherServiceEntity.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private PriorityServiceEntity createPriorityService() {
        return PriorityServiceEntity.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private MaximumEntity createMaximum() {

        return MaximumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private MinimumEntity createMinimum() {

        return MinimumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private PriceEntity createPrice() {
        return PriceEntity.builder()
                .interval("123")
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private CustomersEntity createCustomers() {

        return CustomersEntity.builder()
                .rate("123")
                .build();
    }

}
