package com.getdata.dataprovider.mapper;

import com.getdata.core.model.OtherService;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriceEntity;
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
public class OtherServiceToOtherServiceEntityMapper implements Converter<OtherService, OtherServiceEntity> {

    private final PriceToPriceEntityMapper priceToPriceEntityMapper;
    private final MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;
    private final MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;

    @Override
    @NonNull
    public OtherServiceEntity convert(final OtherService otherService) {

        String chargingTriggerInfo = otherService.getChargingTriggerInfo() != null && otherService.getChargingTriggerInfo().length() >= 255 ?
                otherService.getChargingTriggerInfo().substring(0, 254) :
                otherService.getChargingTriggerInfo();

        return OtherServiceEntity.builder()
                .name(otherService.getName())
                .code(otherService.getCode())
                .chargingTriggerInfo(chargingTriggerInfo)
                .prices(convertListOfPricesToListOfPricesEntity(otherService.getPrices()))
                .minimum(minimumToMinimumEntityMapper.convert(otherService.getMinimum()))
                .maximum(maximumToMaximumEntityMapper.convert(otherService.getMaximum()))
                .build();
    }

    private List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(priceToPriceEntityMapper::convert).collect(Collectors.toList());

    }
}
