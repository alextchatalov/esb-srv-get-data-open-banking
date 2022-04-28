package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.OpeningClosingChannel;
import com.getdata.dataprovider.entity.TransactionMethod;
import com.getdata.dataprovider.entity.TypeAccount;
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

public class PersonalAccountEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {

        Fixture.of(PersonalAccountEntity.class).addTemplate(VALID, new Rule() {{
            PersonalAccountEntity personalAccountEntity = personalAccountEntityBuilder().build();
            add(PersonalAccountEntity.Fields.type, personalAccountEntity.getType());
            add("fees", personalAccountEntity.getFees());
            add(PersonalAccountEntity.Fields.serviceBundles, personalAccountEntity.getServiceBundles());
            add(PersonalAccountEntity.Fields.openingClosingChannels, personalAccountEntity.getOpeningClosingChannels());
            add(PersonalAccountEntity.Fields.additionalInfo, personalAccountEntity.getAdditionalInfo());
            add(PersonalAccountEntity.Fields.transactionMethods, personalAccountEntity.getTransactionMethods());
            add("termsConditions", personalAccountEntity.getTermsConditions());
            add(PersonalAccountEntity.Fields.incomeRate, personalAccountEntity.getIncomeRate());

        }});
    }

    private PersonalAccountEntity.PersonalAccountEntityBuilder personalAccountEntityBuilder() {

        PersonalAccountEntity.PersonalAccountEntityBuilder personalAccountEntityBuilder = PersonalAccountEntity.builder()
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA)
                .fees(createFees())
                .serviceBundles(Arrays.asList(createServiceBundles()))
                .additionalInfo("test")
                .termsConditions(createTermsConditions())
                .incomeRate(Arrays.asList(createIncomeRate()));

        personalAccountEntityBuilder.openingClosingChannels(createOpeningClosingChannels(personalAccountEntityBuilder.build()));
        personalAccountEntityBuilder.transactionMethods(createTransactionMethods(personalAccountEntityBuilder.build()));

        return personalAccountEntityBuilder;
    }

    private List<OpeningClosingChannelsEntity> createOpeningClosingChannels(final PersonalAccountEntity personalAccount) {

        return Arrays.asList(OpeningClosingChannelsEntity.builder()
                .channel(OpeningClosingChannel.CHAT)
                .personalAccount(personalAccount)
                .businessAccount(null)
                .build());
    }

    private List<TransactionMethodsEntity> createTransactionMethods(final PersonalAccountEntity personalAccount) {

        return Arrays.asList(TransactionMethodsEntity.builder()
                .method(TransactionMethod.MOVIMENTACAO_ELETRONICA)
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
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
                .chargingTriggerInfo("test")
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private PriorityServiceEntity createPriorityService() {
        return PriorityServiceEntity.builder()
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
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
                .interval(Interval.FAIXA_1)
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

    private MinimumBalanceEntity createMinimumBalance() {
        return MinimumBalanceEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }
}