package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Brand;
import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.Company;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Data;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.MinimumBalance;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.Participant;
import com.getdata.core.model.ParticipantStatus;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.OpeningClosingChannel;
import com.getdata.dataprovider.entity.TransactionMethod;
import com.getdata.dataprovider.entity.TypeAccount;

import java.util.Collections;

public class DataFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Data.class).addTemplate(VALID, new Rule() {{
            add(Data.Fields.brand, createBrand());

        }});
    }

    private Brand createBrand() {

        return Brand.builder()
                .name("test")
                .companies(Collections.singletonList(createCompany()))
                .participant(createParticipant())
                .build();
    }

    private Participant createParticipant() {
        return Participant.builder()
                .organisationId("123")
                .status(ParticipantStatus.ACTIVE)
                .organisationName("test")
                .customerFriendlyName("test")
                .customerFriendlyLogoUri("test")
                .build();
    }

    private Company createCompany() {

        return Company.builder()
                .name("test")
                .cnpjNumber("1234444456")
                .urlComplementaryList("test")
                .personalAccounts(Collections.singletonList(createPersonalAccount()))
                .businessAccounts(Collections.singletonList(createBusinessAccount()))
                .build();
    }

    private BusinessAccount createBusinessAccount() {
        return BusinessAccount.builder()
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA.name())
                .fees(createFeesBusiness())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .openingClosingChannels(Collections.singletonList(OpeningClosingChannel.CHAT.name()))
                .additionalInfo("test")
                .transactionMethods(Collections.singletonList(TransactionMethod.MOVIMENTACAO_ELETRONICA.name()))
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
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA.name())
                .fees(createFees())
                .serviceBundles(Collections.singletonList(createServiceBundles()))
                .openingClosingChannels(Collections.singletonList(OpeningClosingChannel.CHAT.name()))
                .additionalInfo("test")
                .transactionMethods(Collections.singletonList(TransactionMethod.MOVIMENTACAO_ELETRONICA.name()))
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
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private PriorityService createPriorityService() {
        return PriorityService.builder()
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
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
                .interval(Interval.FAIXA_1.getOriginal())
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

    private MinimumBalance createMinimumBalance() {
        return MinimumBalance.builder()
                .currency("123")
                .value("123")
                .build();
    }
}
