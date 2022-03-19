package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Customers;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class CustomersToCustomersEntityMapper {

    @NonNull
    public static CustomersEntity convert(final Customers customers, final PriceEntity priceEntity) {
        return CustomersEntity.builder()
                .rate(customers.getRate())
                .price(priceEntity)
                .build();
    }

}
