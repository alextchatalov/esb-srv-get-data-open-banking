package com.getdata.dataprovider.mapper;

import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class BusinessAccountToBusinessAccountEntityMapper implements Converter<BusinessAccount, BusinessAccountEntity> {

    private final FeesBusinessAccountsToFeesBusinessAccountsEntityMapper feesBusinessAccountsToFeesBusinessAccountsEntityMapper;
    private final ServiceBundleToServiceBundleEntityMapper serviceBundleToServiceBundleEntityMapper;
    private final TermsConditionsToTermsConditionsEntityMapper termsConditionsToTermsConditionsEntityMapper;
    private final IncomeRateToIncomeRateEntityMapper incomeRateToIncomeRateEntityMapper;

    @Override
    @NonNull
    public BusinessAccountEntity convert(final BusinessAccount businessAccount) {
        BusinessAccountEntity businessAccountEntity = BusinessAccountEntity.builder()
                .type(businessAccount.getType())
                .fees(feesBusinessAccountsToFeesBusinessAccountsEntityMapper.convert(businessAccount.getFees()))
                .serviceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(businessAccount.getServiceBundles()))
                .additionalInfo(businessAccount.getAdditionalInfo())
                .termsConditions(termsConditionsToTermsConditionsEntityMapper.convert(businessAccount.getTermsConditions()))
                .incomeRate(incomeRateToIncomeRateEntityMapper.convert(businessAccount.getIncomeRate()))
                .build();

        List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convert(businessAccount.getOpeningClosingChannels(), null, businessAccountEntity);
        List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convert(businessAccount.getTransactionMethods(), null, businessAccountEntity);
        businessAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        businessAccountEntity.setTransactionMethods(transactionMethodsEntities);

        return businessAccountEntity;
    }

    private List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles) {
        return serviceBundles.stream().map(serviceBundleToServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

}
