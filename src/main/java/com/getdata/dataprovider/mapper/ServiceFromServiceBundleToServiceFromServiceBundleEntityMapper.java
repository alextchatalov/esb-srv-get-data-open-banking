package com.getdata.dataprovider.mapper;

import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper {

    @NonNull
    public static ServiceFromServiceBundleEntity convert(final ServiceFromServiceBundle services) {

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
