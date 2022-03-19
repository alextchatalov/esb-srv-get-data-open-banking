package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class PriceToPriceEntityMapper {

    @NonNull
    public static PriceEntity convertWithPriorityService(final Price price, final PriorityServiceEntity priorityServiceEntity) {
        return convert(price, priorityServiceEntity, null, null, null);
    }

    @NonNull
    public static PriceEntity convertWithOtherService(final Price price, final OtherServiceEntity otherServiceEntity) {
        return convert(price, null, otherServiceEntity, null, null);
    }

    @NonNull
    public static PriceEntity convertWithServiceBundle(final Price price, final ServiceBundleEntity serviceBundleEntity) {
        return convert(price, null, null, serviceBundleEntity, null);
    }

    @NonNull
    public static PriceEntity convertWithServiceBusinessAccounts(final Price price, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {
        return convert(price, null, null, null, serviceBusinessAccountsEntity);
    }

    public static PriceEntity convert(final Price price, final PriorityServiceEntity priorityServiceEntity, final OtherServiceEntity otherServiceEntity, final ServiceBundleEntity serviceBundleEntity, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {

        final PriceEntity priceEntity = PriceEntity.builder()
                .interval(price.getInterval())
                .value(price.getValue())
                .currency(price.getCurrency())
                .monthlyFee(price.getMonthlyFee())
                .priorityService(priorityServiceEntity)
                .serviceBundle(serviceBundleEntity)
                .otherService(otherServiceEntity)
                .serviceBusinessAccounts(serviceBusinessAccountsEntity)
                .build();

        priceEntity.setCustomers(CustomersToCustomersEntityMapper.convert(price.getCustomers(), priceEntity));

        return priceEntity;
    }

}
