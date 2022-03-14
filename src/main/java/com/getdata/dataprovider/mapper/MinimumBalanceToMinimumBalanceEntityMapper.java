package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.core.model.MinimumBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class MinimumBalanceToMinimumBalanceEntityMapper implements Converter<MinimumBalance, MinimumBalanceEntity> {


    @Override
    @NonNull
    public MinimumBalanceEntity convert(final MinimumBalance minimumBalance) {
        return MinimumBalanceEntity.builder()
                .value(minimumBalance.getValue())
                .currency(minimumBalance.getCurrency())
                .build();
    }

}
