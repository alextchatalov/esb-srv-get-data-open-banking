package com.getdata.dataprovider.mapper;

import com.getdata.core.model.OtherService;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class OtherServiceToOtherServiceEntityMapper {

    @NonNull
    public static OtherServiceEntity convert(final OtherService otherService) {

        String chargingTriggerInfo = otherService.getChargingTriggerInfo() != null && otherService.getChargingTriggerInfo().length() >= 255 ?
                otherService.getChargingTriggerInfo().substring(0, 254) :
                otherService.getChargingTriggerInfo();

        return OtherServiceEntity.builder()
                .name(otherService.getName())
                .code(otherService.getCode())
                .chargingTriggerInfo(chargingTriggerInfo)
                .prices(convertListOfPricesToListOfPricesEntity(otherService.getPrices()))
                .minimum(MinimumToMinimumEntityMapper.convert(otherService.getMinimum()))
                .maximum(MaximumToMaximumEntityMapper.convert(otherService.getMaximum()))
                .build();
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(PriceToPriceEntityMapper::convert).collect(Collectors.toList());

    }
}
