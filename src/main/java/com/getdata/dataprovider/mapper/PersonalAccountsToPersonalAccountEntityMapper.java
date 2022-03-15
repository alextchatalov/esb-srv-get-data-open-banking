package com.getdata.dataprovider.mapper;

import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
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
public class PersonalAccountsToPersonalAccountEntityMapper implements Converter<PersonalAccount, PersonalAccountEntity> {

    private final FeesPersonalAccountsToFeesPersonalAccountsEntityMapper feesPersonalAccountsToFeesPersonalAccountsEntityMapper;
    private final ServiceBundleToServiceBundleEntityMapper serviceBundleToServiceBundleEntityMapper;
    private final TermsConditionsToTermsConditionsEntityMapper termsConditionsToTermsConditionsEntityMapper;
    private final IncomeRateToIncomeRateEntityMapper incomeRateToIncomeRateEntityMapper;

    @Override
    @NonNull
    public PersonalAccountEntity convert(final PersonalAccount personalAccount) {

        PersonalAccountEntity personalAccountEntity = PersonalAccountEntity.builder()
                .type(personalAccount.getType())
                .fees(feesPersonalAccountsToFeesPersonalAccountsEntityMapper.convert(personalAccount.getFees()))
                .serviceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(personalAccount.getServiceBundles()))
                .additionalInfo(personalAccount.getAdditionalInfo())
                .termsConditions(termsConditionsToTermsConditionsEntityMapper.convert(personalAccount.getTermsConditions()))
                .incomeRate(convertListOfIncomeRateToListOfIncomeRateEntity(personalAccount.getIncomeRate()))
                .build();
        
        List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convert(personalAccount.getOpeningClosingChannels(), personalAccountEntity, null);
        List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convert(personalAccount.getTransactionMethods(), personalAccountEntity, null);
        personalAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        personalAccountEntity.setTransactionMethods(transactionMethodsEntities);

        return personalAccountEntity;
    }

    private List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles) {
        return serviceBundles.stream().map(serviceBundleToServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

    private List<IncomeRateEntity> convertListOfIncomeRateToListOfIncomeRateEntity(final List<IncomeRate> incomeRates) {
        return incomeRates.stream().map(incomeRateToIncomeRateEntityMapper::convert).collect(Collectors.toList());
    }

}
