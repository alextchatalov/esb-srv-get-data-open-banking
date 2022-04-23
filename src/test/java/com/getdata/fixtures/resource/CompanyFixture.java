package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Application;
import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.BusinessLoan;
import com.getdata.core.model.Company;
import com.getdata.core.model.Customers;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.FeesLoan;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.Indexer;
import com.getdata.core.model.InterestRate;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.MinimumBalance;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.PersonalLoan;
import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.core.model.ServiceLoans;
import com.getdata.core.model.TermsConditions;

import java.util.Arrays;
import java.util.Collections;

public class CompanyFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Company.class).addTemplate(VALID, new Rule() {{
            add(Company.Fields.name, "test");
            add(Company.Fields.cnpjNumber, "1244441");
            add(Company.Fields.urlComplementaryList, "test");
            add(Company.Fields.personalAccounts, Arrays.asList(createPersonalAccount()));
            add(Company.Fields.businessAccounts, Arrays.asList(createBusinessAccount()));
            add(Company.Fields.personalLoans, Arrays.asList(createPersonalLoan()));
            add(Company.Fields.businessLoans, Arrays.asList(createBusinessLoans()));
        }});
    }

    private BusinessLoan createBusinessLoans() {
        return BusinessLoan.builder()
                .type("EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS")
                .fees(createFeesLoan())
                .interestRates(Arrays.asList(createInterestRate()))
                .requiredWarranties(Arrays.asList("test"))
                .termsConditions("test")
                .build();
    }

    private PersonalLoan createPersonalLoan() {
        return PersonalLoan.builder()
                .type("EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS")
                .fees(createFeesLoan())
                .interestRates(Arrays.asList(createInterestRate()))
                .requiredWarranties(Arrays.asList("test"))
                .termsConditions("test")
                .build();
    }

    private InterestRate createInterestRate() {
        return InterestRate.builder()
                .referentialRateIndexer("test")
                .rate("1")
                .applications(Arrays.asList(createApplication()))
                .minimumRate("1")
                .maximumRate("1")
                .build();
    }

    private Application createApplication() {
        return Application.builder()
                .interval("1")
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
    }

    private Indexer createIndexer() {
        return Indexer.builder()
                .rate("1")
                .build();
    }

    private FeesLoan createFeesLoan() {
        return FeesLoan.builder()
                .services(Arrays.asList(createServicePersonalLoans()))
                .build();
    }

    private ServiceLoans createServicePersonalLoans() {
        return ServiceLoans.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Arrays.asList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private BusinessAccount createBusinessAccount() {
        return BusinessAccount.builder()
                .type("CONTA_DEPOSITO_A_VISTA")
                .fees(createFeesBusiness())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .openingClosingChannels(Collections.singletonList("test"))
                .additionalInfo("test")
                .transactionMethods(Collections.singletonList("test"))
                .termsConditions(createTermsConditions())
                .incomeRate(createIncomeRate())
                .build();
    }

    private FeesBusinessAccounts createFeesBusiness() {

        return FeesBusinessAccounts.builder()
                .services(Collections.singletonList(createServiceBusiness()))
                .build();
    }

    private ServiceBusinessAccounts createServiceBusiness() {

        return ServiceBusinessAccounts.builder()
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

    private PersonalAccount createPersonalAccount() {

        return PersonalAccount.builder()
                .type("CONTA_DEPOSITO_A_VISTA")
                .fees(createFees())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .openingClosingChannels(Collections.singletonList("test"))
                .additionalInfo("test")
                .transactionMethods(Collections.singletonList("test"))
                .termsConditions(createTermsConditions())
                .incomeRate(Collections.singletonList(createIncomeRate()))
                .build();
    }

    private IncomeRate createIncomeRate() {

        return IncomeRate.builder()
                .savingAccount("123")
                .prepaidPaymentAccount("123")
                .build();
    }

    private TermsConditions createTermsConditions() {

        return TermsConditions.builder()
                .minimumBalance(createMinimumBalance())
                .elegibilityCriteriaInfo("test")
                .closingProcessInfo("1")
                .build();
    }

    private MinimumBalance createMinimumBalance() {
        return MinimumBalance.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private ServiceBundle createServiceBundles() {

        return ServiceBundle.builder()
                .name("test")
                .services(Collections.singletonList(createService()))
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private ServiceFromServiceBundle createService() {

        return ServiceFromServiceBundle.builder()
                .code("123")
                .chargingTriggerInfo("test")
                .eventLimitQuantity("1")
                .freeEventQuantity("1")
                .build();
    }


    private FeesPersonalAccounts createFees() {

        return FeesPersonalAccounts.builder()
                .priorityServices(Collections.singletonList(createPriorityService()))
                .otherServices(Collections.singletonList(createOtherService()))
                .build();
    }

    private OtherService createOtherService() {

        return OtherService.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private PriorityService createPriorityService() {
        return PriorityService.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private Maximum createMaximum() {

        return Maximum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Minimum createMinimum() {

        return Minimum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Price createPrice() {
        return Price.builder()
                .interval("123")
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private Customers createCustomers() {

        return Customers.builder()
                .rate("123")
                .build();
    }

}
