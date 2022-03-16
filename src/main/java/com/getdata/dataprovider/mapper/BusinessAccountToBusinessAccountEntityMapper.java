package com.getdata.dataprovider.mapper;

import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
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
    public static BusinessAccountEntity convert(final BusinessAccount businessAccount) {
        BusinessAccountEntity businessAccountEntity = BusinessAccountEntity.builder()
                .type(businessAccount.getType())
                .fees(FeesBusinessAccountsToFeesBusinessAccountsEntityMapper.convert(businessAccount.getFees()))
                .serviceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(businessAccount.getServiceBundles()))
                .additionalInfo(businessAccount.getAdditionalInfo())
                .termsConditions(TermsConditionsToTermsConditionsEntityMapper.convert(businessAccount.getTermsConditions()))
                .incomeRate(IncomeRateToIncomeRateEntityMapper.convert(businessAccount.getIncomeRate()))
                .build();

        List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convert(businessAccount.getOpeningClosingChannels(), null, businessAccountEntity);
        List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convert(businessAccount.getTransactionMethods(), null, businessAccountEntity);
        businessAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        businessAccountEntity.setTransactionMethods(transactionMethodsEntities);

        return businessAccountEntity;
    }

    private static List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles) {
        return serviceBundles.stream().map(ServiceBundleToServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

}
