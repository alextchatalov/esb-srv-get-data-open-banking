package com.getdata.dataprovider.mapper;

import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class PersonalAccountsToPersonalAccountEntityMapper {

    @NonNull
    public static PersonalAccountEntity convert(final PersonalAccount personalAccount) {

        PersonalAccountEntity personalAccountEntity = PersonalAccountEntity.builder()
                .type(personalAccount.getType())
                .fees(FeesPersonalAccountsToFeesPersonalAccountsEntityMapper.convert(personalAccount.getFees()))
                .serviceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(personalAccount.getServiceBundles()))
                .additionalInfo(personalAccount.getAdditionalInfo())
                .termsConditions(TermsConditionsToTermsConditionsEntityMapper.convert(personalAccount.getTermsConditions()))
                .incomeRate(convertListOfIncomeRateToListOfIncomeRateEntity(personalAccount.getIncomeRate()))
                .build();

        List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convert(personalAccount.getOpeningClosingChannels(), personalAccountEntity, null);
        List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convert(personalAccount.getTransactionMethods(), personalAccountEntity, null);
        personalAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        personalAccountEntity.setTransactionMethods(transactionMethodsEntities);

        return personalAccountEntity;
    }

    private static List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles) {
        return serviceBundles.stream().map(ServiceBundleToServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

    private static List<IncomeRateEntity> convertListOfIncomeRateToListOfIncomeRateEntity(final List<IncomeRate> incomeRates) {
        return incomeRates.stream().map(IncomeRateToIncomeRateEntityMapper::convert).collect(Collectors.toList());
    }

}
