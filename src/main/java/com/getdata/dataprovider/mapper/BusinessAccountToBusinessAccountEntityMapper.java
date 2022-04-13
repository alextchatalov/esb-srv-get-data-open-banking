package com.getdata.dataprovider.mapper;

import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class BusinessAccountToBusinessAccountEntityMapper {

    @NonNull
    public static BusinessAccountEntity convert(final BusinessAccount businessAccount, final CompanyEntity companyEntity) {

        final BusinessAccountEntity businessAccountEntity = BusinessAccountEntity.builder()
                .company(companyEntity)
                .type(businessAccount.getType())
                .additionalInfo(businessAccount.getAdditionalInfo())
                .build();

        final List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convertWithBusinessAccounts(businessAccount.getOpeningClosingChannels(), businessAccountEntity);
        final List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convertWithBusinessAccounts(businessAccount.getTransactionMethods(), businessAccountEntity);
        businessAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        businessAccountEntity.setTransactionMethods(transactionMethodsEntities);
        businessAccountEntity.setFees(FeesBusinessAccountsToFeesBusinessAccountsEntityMapper.convert(businessAccount.getFees(), businessAccountEntity));
        businessAccountEntity.setServiceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(businessAccount.getServiceBundles(), businessAccountEntity));
        businessAccountEntity.setTermsConditions(TermsConditionsToTermsConditionsEntityMapper.convertWithBusinessAccounts(businessAccount.getTermsConditions(), businessAccountEntity));
        businessAccountEntity.setIncomeRate(IncomeRateToIncomeRateEntityMapper.convertWithBusinessAccounts(businessAccount.getIncomeRate(), businessAccountEntity));

        return businessAccountEntity;
    }

    private static List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles, final BusinessAccountEntity businessAccountEntity) {
        if (serviceBundles != null && !serviceBundles.isEmpty()) {
            return serviceBundles.stream().map(service -> ServiceBundleToServiceBundleEntityMapper.convertWithBusinessAccounts(service, businessAccountEntity)).collect(Collectors.toList());
        }
        return null;
    }

}
