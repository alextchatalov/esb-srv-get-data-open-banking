package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Maximum;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class MaximumToMaximumEntityMapper {

    @NonNull
    public static MaximumEntity convertWithPriorityService(final Maximum maximum, final PriorityServiceEntity priorityServiceEntity) {
        return convert(maximum, priorityServiceEntity, null, null, null);
    }

    @NonNull
    public static MaximumEntity convertWithOtherService(final Maximum maximum, final OtherServiceEntity otherServiceEntity) {
        return convert(maximum, null, otherServiceEntity, null, null);
    }

    @NonNull
    public static MaximumEntity convertWithServiceBundle(final Maximum maximum, final ServiceBundleEntity serviceBundleEntity) {
        return convert(maximum, null, null, serviceBundleEntity, null);
    }

    @NonNull
    public static MaximumEntity convertWithServiceBusinessAccounts(final Maximum maximum, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {
        return convert(maximum, null, null, null, serviceBusinessAccountsEntity);
    }

    public static MaximumEntity convert(final Maximum maximum, final PriorityServiceEntity priorityServiceEntity, final OtherServiceEntity otherServiceEntity, final ServiceBundleEntity serviceBundleEntity, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {
        return MaximumEntity.builder()
                .priorityService(priorityServiceEntity)
                .otherService(otherServiceEntity)
                .serviceBundle(serviceBundleEntity)
                .serviceBusinessAccounts(serviceBusinessAccountsEntity)
                .value(maximum.getValue())
                .currency(maximum.getCurrency())
                .build();
    }
}
