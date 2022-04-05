package com.getdata.dataprovider.mapper;

import com.getdata.core.model.IncomeRate;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class IncomeRateToIncomeRateEntityMapper {

    @NonNull
    public static IncomeRateEntity convertWithPersonalAccounts(final IncomeRate incomeRate, final PersonalAccountEntity personalAccountEntity) {
        return convert(incomeRate, personalAccountEntity, null);
    }

    @NonNull
    public static IncomeRateEntity convertWithBusinessAccounts(final IncomeRate incomeRate, final BusinessAccountEntity businessAccountEntity) {
        return convert(incomeRate, null, businessAccountEntity);
    }

    private static IncomeRateEntity convert(final IncomeRate incomeRate, final PersonalAccountEntity personalAccountEntity, final BusinessAccountEntity businessAccountEntity) {

        final String savingsAccount = incomeRate.getSavingAccount() != null && incomeRate.getSavingAccount().length() >= 255 ?
                incomeRate.getSavingAccount().substring(0, 254) :
                incomeRate.getSavingAccount();

        return IncomeRateEntity.builder()
                .personalAccount(personalAccountEntity)
                .businessAccount(businessAccountEntity)
                .savingAccount(savingsAccount)
                .prepaidPaymentAccount(incomeRate.getPrepaidPaymentAccount())
                .build();
    }

}
