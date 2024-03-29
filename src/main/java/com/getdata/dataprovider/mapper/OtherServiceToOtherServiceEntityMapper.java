package com.getdata.dataprovider.mapper;

import com.getdata.core.model.OtherService;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
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
    public static OtherServiceEntity convert(final OtherService otherService, final FeesPersonalAccountsEntity feesPersonalAccountsEntity) {

        final OtherServiceEntity otherServiceEntity = OtherServiceEntity.builder()
                .feesPersonalAccounts(feesPersonalAccountsEntity)
                .name(otherService.getName())
                .code(otherService.getCode())
                .chargingTriggerInfo(otherService.getChargingTriggerInfo())
                .build();

        otherServiceEntity.setPrices(convertListOfPricesToListOfPricesEntity(otherService.getPrices(), otherServiceEntity));
        otherServiceEntity.setMinimum(MinimumToMinimumEntityMapper.convertWithOtherService(otherService.getMinimum(), otherServiceEntity));
        otherServiceEntity.setMaximum(MaximumToMaximumEntityMapper.convertWithOtherService(otherService.getMaximum(), otherServiceEntity));

        return otherServiceEntity;
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices, final OtherServiceEntity otherServiceEntity) {
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithOtherService(price, otherServiceEntity)).collect(Collectors.toList());

    }
}
