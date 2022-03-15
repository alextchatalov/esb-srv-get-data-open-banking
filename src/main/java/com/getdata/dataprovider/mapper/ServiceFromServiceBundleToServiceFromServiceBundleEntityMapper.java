package com.getdata.dataprovider.mapper;

import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
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

        String chargingTriggerInfo = services.getChargingTriggerInfo() != null && services.getChargingTriggerInfo().length() >= 255 ?
                services.getChargingTriggerInfo().substring(0, 254) :
                services.getChargingTriggerInfo();

        return ServiceFromServiceBundleEntity.builder()
                .code(services.getCode())
                .chargingTriggerInfo(chargingTriggerInfo)
                .eventLimitQuantity(services.getEventLimitQuantity())
                .freeEventQuantity(services.getFreeEventQuantity())
                .build();
    }

}
