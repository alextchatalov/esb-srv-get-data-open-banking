package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.ParticipantStatus;
import com.getdata.dataprovider.entity.ApiEndPointEntity;
import com.getdata.dataprovider.entity.ApiResourceEntity;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannel;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
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

public class DataEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(DataEntity.class).addTemplate(VALID, new Rule() {{
            add(DataEntity.Fields.id, "123");
            add(DataEntity.Fields.brand, createBrand());
        }});
    }

    private BrandEntity createBrand() {

        return BrandEntity.builder()
                .name("test")
                .companies(Collections.singletonList(createCompany()))
                .participant(createParticipant())
                .build();
    }

    private ParticipantEntity createParticipant() {

        final ParticipantEntity participantEntity = ParticipantEntity.builder()
                .organisationId("123")
                .status(ParticipantStatus.ACTIVE)
                .organisationName("test")
                .customerFriendlyName("test")
                .customerFriendlyLogoUri("test")
                .build();

        participantEntity.setApiResources(Arrays.asList(createApiResources(participantEntity)));
        
        return participantEntity;
    }

    private ApiResourceEntity createApiResources(final ParticipantEntity participantMock) {

        final ApiResourceEntity apiResourceEntityMock = ApiResourceEntity.builder()
                .apiFamilyType("test")
                .apiVersion("1")
                .participant(participantMock)
                .build();

        final List<ApiEndPointEntity> apiEndPointEntity = createApiEndPointEntity(apiResourceEntityMock);
        apiResourceEntityMock.setApiEndpoint(apiEndPointEntity);
        return apiResourceEntityMock;
    }

    private List<ApiEndPointEntity> createApiEndPointEntity(final ApiResourceEntity apiResourceEntityMock) {

        return Arrays.asList(ApiEndPointEntity.builder()
                .id(1L)
                .endpoint("123")
                .apiResource(apiResourceEntityMock)
                .build());
    }

    private CompanyEntity createCompany() {

        return CompanyEntity.builder()
                .name("test")
                .cnpjNumber("1234444456")
                .urlComplementaryList("test")
                .personalAccounts(Collections.singletonList(createPersonalAccount()))
                .businessAccounts(Collections.singletonList(createBusinessAccount()))
                .build();
    }

    private BusinessAccountEntity createBusinessAccount() {
        final BusinessAccountEntity businessAccountEntity = BusinessAccountEntity.builder()
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA)
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

    private PersonalAccountEntity createPersonalAccount() {

        final PersonalAccountEntity personalAccountEntity = PersonalAccountEntity.builder()
                .type(TypeAccount.CONTA_DEPOSITO_A_VISTA)
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
                .channel(OpeningClosingChannel.CHAT)
                .personalAccount(personalAccount)
                .businessAccount(businessAccountEntity)
                .build());
    }

    private List<TransactionMethodsEntity> createTransactionMethods(final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccountEntity) {

        return Arrays.asList(TransactionMethodsEntity.builder()
                .method(TransactionMethod.MOVIMENTACAO_ELETRONICA)
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
