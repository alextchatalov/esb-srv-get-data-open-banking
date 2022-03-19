package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Minimum;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class MinimumToMinimumEntityMapper {

    @NonNull
    public static MinimumEntity convertWithPriorityService(final Minimum minimum, final PriorityServiceEntity priorityServiceEntity) {
        return convert(minimum, priorityServiceEntity, null, null, null);
    }

    @NonNull
    public static MinimumEntity convertWithOtherService(final Minimum minimum, final OtherServiceEntity otherServiceEntity) {
        return convert(minimum, null, otherServiceEntity, null, null);
    }

    @NonNull
    public static MinimumEntity convertWithServiceBundle(final Minimum minimum, final ServiceBundleEntity serviceBundleEntity) {
        return convert(minimum, null, null, serviceBundleEntity, null);
    }

    @NonNull
    public static MinimumEntity convertWithServiceBusinessAccounts(final Minimum minimum, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {
        return convert(minimum, null, null, null, serviceBusinessAccountsEntity);
    }

    public static MinimumEntity convert(final Minimum minimum, final PriorityServiceEntity priorityServiceEntity, final OtherServiceEntity otherServiceEntity, final ServiceBundleEntity serviceBundleEntity, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {

        return MinimumEntity.builder()
                .priorityService(priorityServiceEntity)
                .otherService(otherServiceEntity)
                .serviceBundle(serviceBundleEntity)
                .serviceBusinessAccounts(serviceBusinessAccountsEntity)
                .value(minimum.getValue())
                .currency(minimum.getCurrency())
                .build();
    }
}
