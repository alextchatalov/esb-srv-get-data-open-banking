package com.getdata.dataprovider.mapper;

import com.getdata.core.model.IncomeRate;
import com.getdata.dataprovider.entity.IncomeRateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class IncomeRateToIncomeRateEntityMapper {

    @NonNull
    public static IncomeRateEntity convert(final IncomeRate incomeRate) {
        return IncomeRateEntity.builder()
                .savingAccount(incomeRate.getSavingAccount())
                .prepaidPaymentAccount(incomeRate.getPrepaidPaymentAccount())
                .build();
    }

}
