package com.getdata.dataprovider.mapper;

import com.getdata.core.model.MinimumBalance;
import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class MinimumBalanceToMinimumBalanceEntityMapper {

    public static MinimumBalanceEntity convert(final MinimumBalance minimumBalance, final TermsConditionsEntity termsConditions) {

        return MinimumBalanceEntity.builder()
                .value(minimumBalance.getValue())
                .currency(minimumBalance.getCurrency())
                .termsConditions(termsConditions)
                .build();
    }

}
