package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class PriorityServiceToPriorityServiceEntityMapper {

    @NonNull
    public static PriorityServiceEntity convert(final PriorityService priorityService) {

        String chargingTriggerInfo = priorityService.getChargingTriggerInfo() != null && priorityService.getChargingTriggerInfo().length() >= 255 ?
                priorityService.getChargingTriggerInfo().substring(0, 254) :
                priorityService.getChargingTriggerInfo();

        return PriorityServiceEntity.builder()
                .name(priorityService.getName())
                .code(priorityService.getCode())
                .chargingTriggerInfo(chargingTriggerInfo)
                .prices(convertListOfPricesToListOfPricesEntity(priorityService.getPrices()))
                .minimum(MinimumToMinimumEntityMapper.convert(priorityService.getMinimum()))
                .maximum(MaximumToMaximumEntityMapper.convert(priorityService.getMaximum()))
                .build();
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(PriceToPriceEntityMapper::convert).collect(Collectors.toList());
    }

}
