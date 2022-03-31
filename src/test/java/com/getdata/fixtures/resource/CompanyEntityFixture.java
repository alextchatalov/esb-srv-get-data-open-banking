package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.FeesPersonalLoanEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.IndexerEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompanyEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(CompanyEntity.class).addTemplate(VALID, new Rule() {{
            add(CompanyEntity.Fields.name, "test");
            add(CompanyEntity.Fields.cnpjNumber, "1244441");
            add(CompanyEntity.Fields.urlComplementaryList, "test");
            add(CompanyEntity.Fields.personalAccounts, Arrays.asList(createPersonalAccount()));
            add(CompanyEntity.Fields.businessAccounts, Arrays.asList(createBusinessAccount()));
            add(CompanyEntity.Fields.personalLoans, Arrays.asList(createPersonalLoan()));
        }});
    }

    private PersonalLoanEntity createPersonalLoan() {
        return PersonalLoanEntity.builder()
                .type("test")
                .fees(createFeesPersonalLoan())
                .interestRates(Arrays.asList(createInterestRate()))
                .requiredWarranties(Arrays.asList(createWarranties()))
                .termsConditions("test")
                .build();
    }

    private RequiredWarrantiesEntity createWarranties() {
        return RequiredWarrantiesEntity.builder()
                .warranties("test")
                .build();
    }

    private InterestRateEntity createInterestRate() {
        return InterestRateEntity.builder()
                .referentialRateIndexer("test")
                .rate("1")
                .applications(Arrays.asList(createApplication()))
                .minimumRate("1")
                .maximumRate("1")
                .build();
    }

    private ApplicationEntity createApplication() {
        return ApplicationEntity.builder()
                .interval("1")
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
    }

    private IndexerEntity createIndexer() {
        return IndexerEntity.builder()
                .rate("1")
                .build();
    }

    private FeesPersonalLoanEntity createFeesPersonalLoan() {
        return FeesPersonalLoanEntity.builder()
                .services(Arrays.asList(createServicePersonalLoans()))
                .build();
    }

    private PriorityServiceEntity createServicePersonalLoans() {
        return PriorityServiceEntity.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Arrays.asList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private BusinessAccountEntity createBusinessAccount() {
        final BusinessAccountEntity businessAccountEntity = BusinessAccountEntity.builder()
                .type("test")
                .fees(createFeesBusiness())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .additionalInfo("test")
                .termsConditions(createTermsConditions())
                .incomeRate(createIncomeRate())
                .build();

        businessAccountEntity.setOpeningClosingChannels(createOpeningClosingChannels(null, businessAccountEntity));
        businessAccountEntity.setTransactionMethods(createTransactionMethods(null, businessAccountEntity));

        return businessAccountEntity;
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

        personalAccountEntity.setOpeningClosingChannels(createOpeningClosingChannels(personalAccountEntity, null));
        personalAccountEntity.setTransactionMethods(createTransactionMethods(personalAccountEntity, null));

        return personalAccountEntity;
    }

    private List<OpeningClosingChannelsEntity> createOpeningClosingChannels(final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccountEntity) {

        return Arrays.asList(OpeningClosingChannelsEntity.builder()
                .channel("test")
                .personalAccount(personalAccount)
                .businessAccount(businessAccountEntity)
                .build());
    }

    private List<TransactionMethodsEntity> createTransactionMethods(final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccountEntity) {

        return Arrays.asList(TransactionMethodsEntity.builder()
                .method("test")
                .personalAccount(personalAccount)
                .businessAccount(businessAccountEntity)
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

    private FeesBusinessAccountsEntity createFeesBusiness() {

        return FeesBusinessAccountsEntity.builder()
                .services(Collections.singletonList(createServiceBusiness()))
                .build();
    }

    private ServiceBusinessAccountsEntity createServiceBusiness() {

        return ServiceBusinessAccountsEntity.builder()
                .name("test")
                .code("123")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
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
