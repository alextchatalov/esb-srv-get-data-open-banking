package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.core.model.ServiceFromServiceBundle;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
@EqualsAndHashCode
public class ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper implements Converter<ServiceFromServiceBundle, ServiceFromServiceBundleEntity> {

    @Override
    @NonNull
    public ServiceFromServiceBundleEntity convert(final ServiceFromServiceBundle services) {
        return ServiceFromServiceBundleEntity.builder()
                .code(services.getCode())
                .chargingTriggerInfo(services.getChargingTriggerInfo())
                .eventLimitQuantity(services.getEventLimitQuantity())
                .freeEventQuantity(services.getFreeEventQuantity())
                .build();
    }

}
