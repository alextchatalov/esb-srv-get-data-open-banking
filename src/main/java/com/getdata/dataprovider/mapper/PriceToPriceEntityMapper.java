package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.core.model.Price;
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
public class PriceToPriceEntityMapper implements Converter<Price, PriceEntity> {

    private final CustomersToCustomersEntityMapper customersToCustomersEntityMapper;

    @Override
    @NonNull
    public PriceEntity convert(final Price price) {
        return PriceEntity.builder()
                .interval(price.getInterval())
                .value(price.getValue())
                .currency(price.getCurrency())
                .customers(customersToCustomersEntityMapper.convert(price.getCustomers()))
                .monthlyFee(price.getMonthlyFee())
                .build();
    }

}
