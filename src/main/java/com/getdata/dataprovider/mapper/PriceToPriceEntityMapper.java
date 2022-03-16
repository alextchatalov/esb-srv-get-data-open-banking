package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class PriceToPriceEntityMapper {
    
    @NonNull
    public static PriceEntity convert(final Price price) {
        return PriceEntity.builder()
                .interval(price.getInterval())
                .value(price.getValue())
                .currency(price.getCurrency())
                .customers(CustomersToCustomersEntityMapper.convert(price.getCustomers()))
                .monthlyFee(price.getMonthlyFee())
                .build();
    }

}
