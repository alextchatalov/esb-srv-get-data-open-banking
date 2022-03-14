package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class PriorityServiceToPriorityServiceEntityMapper implements Converter<PriorityService, PriorityServiceEntity> {

    private final PriceToPriceEntityMapper priceToPriceEntityMapper;
    private final MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;
    private final MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;

    @Override
    @NonNull
    public PriorityServiceEntity convert(final PriorityService priorityService) {
        return PriorityServiceEntity.builder()
                .name(priorityService.getName())
                .code(priorityService.getCode())
                .chargingTriggerInfo(priorityService.getChargingTriggerInfo())
                .prices(convertListOfPricesToListOfPricesEntity(priorityService.getPrices()))
                .minimum(minimumToMinimumEntityMapper.convert(priorityService.getMinimum()))
                .maximum(maximumToMaximumEntityMapper.convert(priorityService.getMaximum()))
                .build();
    }

    private List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(priceToPriceEntityMapper::convert).collect(Collectors.toList());
    }

}
