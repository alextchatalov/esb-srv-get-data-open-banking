package com.getdata.dataprovider.mapper;

import com.getdata.core.model.IncomeRate;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.ServiceBundle;
import com.getdata.dataprovider.entity.TypeAccount;
import com.getdata.dataprovider.entity.CompanyEntity;
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
    public static PersonalAccountEntity convert(final PersonalAccount personalAccount, final CompanyEntity company) {

        final String additionalInfo = personalAccount.getAdditionalInfo() != null && personalAccount.getAdditionalInfo().length() >= 255 ?
                personalAccount.getAdditionalInfo().substring(0, 254) :
                personalAccount.getAdditionalInfo();

        final PersonalAccountEntity personalAccountEntity = PersonalAccountEntity.builder()
                .type(TypeAccount.valueOf(personalAccount.getType()))
                .additionalInfo(additionalInfo)
                .company(company)
                .build();

        final List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = OpeningClosingChannelsToOpeningClosingChannelsEntity.convertWithPersonalAccounts(personalAccount.getOpeningClosingChannels(), personalAccountEntity);
        final List<TransactionMethodsEntity> transactionMethodsEntities = TransactionMethodsToTransactionMethodsEntity.convertWithPersonalAccounts(personalAccount.getTransactionMethods(), personalAccountEntity);
        personalAccountEntity.setFees(FeesPersonalAccountsToFeesPersonalAccountsEntityMapper.convert(personalAccount.getFees(), personalAccountEntity));
        personalAccountEntity.setServiceBundles(convertListOfServiceBundleToListOfServiceBundleEntity(personalAccount.getServiceBundles(), personalAccountEntity));
        personalAccountEntity.setTermsConditions(TermsConditionsToTermsConditionsEntityMapper.convertWithPersonalAccounts(personalAccount.getTermsConditions(), personalAccountEntity));
        personalAccountEntity.setIncomeRate(convertListOfIncomeRateToListOfIncomeRateEntity(personalAccount.getIncomeRate(), personalAccountEntity));
        personalAccountEntity.setOpeningClosingChannels(openingClosingChannelsEntities);
        personalAccountEntity.setTransactionMethods(transactionMethodsEntities);


        return personalAccountEntity;
    }

    private static List<ServiceBundleEntity> convertListOfServiceBundleToListOfServiceBundleEntity(final List<ServiceBundle> serviceBundles, final PersonalAccountEntity personalAccountEntity) {
        if (serviceBundles == null || serviceBundles.isEmpty()) {
            return null;
        }
        return serviceBundles.stream().map(service -> ServiceBundleToServiceBundleEntityMapper.convertWithPersonalAccounts(service, personalAccountEntity)).collect(Collectors.toList());
    }

    private static List<IncomeRateEntity> convertListOfIncomeRateToListOfIncomeRateEntity(final List<IncomeRate> incomeRates, final PersonalAccountEntity personalAccountEntity) {
        if (incomeRates == null || incomeRates.isEmpty()) {
            return null;
        }
        return incomeRates.stream().map(income -> IncomeRateToIncomeRateEntityMapper.convertWithPersonalAccounts(income, personalAccountEntity)).collect(Collectors.toList());
    }

}
