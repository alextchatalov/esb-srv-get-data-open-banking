package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannel;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.dataprovider.entity.TransactionMethod;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;
import com.getdata.dataprovider.entity.TypeAccount;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BusinessAccountEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        final BusinessAccountEntity businessAccountEntity = createBusinessAccountEntityBuilder().build();

        Fixture.of(BusinessAccountEntity.class).addTemplate(VALID, new Rule() {{
            add(BusinessAccountEntity.Fields.type, businessAccountEntity.getType());
            add(BusinessAccountEntity.Fields.fees, businessAccountEntity.getFees());
            add(BusinessAccountEntity.Fields.serviceBundles, businessAccountEntity.getServiceBundles());
            add(BusinessAccountEntity.Fields.openingClosingChannels, businessAccountEntity.getOpeningClosingChannels());
            add(BusinessAccountEntity.Fields.additionalInfo, businessAccountEntity.getAdditionalInfo());
            add(BusinessAccountEntity.Fields.transactionMethods, businessAccountEntity.getTransactionMethods());
            add(BusinessAccountEntity.Fields.termsConditions, businessAccountEntity.getTermsConditions());
            add(BusinessAccountEntity.Fields.incomeRate, businessAccountEntity.getIncomeRate());

        }});
    }

    private BusinessAccountEntity.BusinessAccountEntityBuilder createBusinessAccountEntityBuilder() {

        final BusinessAccountEntity.BusinessAccountEntityBuilder businessAccountEntityBuilder = BusinessAccountEntity.builder()
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA)
                .fees(createFees())
                .serviceBundles(Arrays.asList(createServiceBundles()))
                .additionalInfo("test")
                .termsConditions(createTermsConditions())
                .incomeRate(createIncomeRate());

        businessAccountEntityBuilder.openingClosingChannels(createOpeningClosingChannels(businessAccountEntityBuilder.build()));
        businessAccountEntityBuilder.transactionMethods(createTransactionMethods(businessAccountEntityBuilder.build()));

        return businessAccountEntityBuilder;
    }

    private List<OpeningClosingChannelsEntity> createOpeningClosingChannels(final BusinessAccountEntity businessAccountEntity) {

        return Arrays.asList(OpeningClosingChannelsEntity.builder()
                .channel(OpeningClosingChannel.CHAT)
                .personalAccount(null)
                .businessAccount(businessAccountEntity)
                .build());
    }

    private List<TransactionMethodsEntity> createTransactionMethods(final BusinessAccountEntity businessAccountEntity) {

        return Arrays.asList(TransactionMethodsEntity.builder()
                .method(TransactionMethod.MOVIMENTACAO_ELETRONICA)
                .personalAccount(null)
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

    private ServiceBundleEntity createServiceBundles() {

        return ServiceBundleEntity.builder()
                .name("test")
                .services(Collections.singletonList(createServiceFromServiceBundle()))
                .prices(Collections.singletonList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private ServiceBusinessAccountsEntity createService() {

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

    private ServiceFromServiceBundleEntity createServiceFromServiceBundle() {

        return ServiceFromServiceBundleEntity.builder()
                .code("123")
                .chargingTriggerInfo("test")
                .eventLimitQuantity("1")
                .freeEventQuantity("1")
                .build();
    }

    private FeesBusinessAccountsEntity createFees() {

        return FeesBusinessAccountsEntity.builder()
                .services(Collections.singletonList(createService()))
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