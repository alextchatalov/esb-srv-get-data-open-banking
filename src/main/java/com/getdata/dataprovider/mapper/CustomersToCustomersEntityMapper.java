package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Customers;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class CustomersToCustomersEntityMapper {

    @NonNull
    public static CustomersEntity convertWithPrice(final Customers customers, final PriceEntity priceEntity) {
        return convert(customers, priceEntity, null);
    }

    @NonNull
    public static CustomersEntity convertWithApplication(final Customers customers, final ApplicationEntity applicationEntity) {
        return convert(customers, null, applicationEntity);
    }

    private static CustomersEntity convert(final Customers customers, final PriceEntity priceEntity, final ApplicationEntity applicationEntity) {
        return CustomersEntity.builder()
                .rate(customers.getRate())
                .price(priceEntity)
                .application(applicationEntity)
                .build();
    }

}
