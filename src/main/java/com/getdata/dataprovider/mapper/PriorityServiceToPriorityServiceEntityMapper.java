package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
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
    public static PriorityServiceEntity convert(final PriorityService priorityService, final FeesPersonalAccountsEntity feesPersonalAccountsEntity) {

        final PriorityServiceEntity priorityServiceEntity = PriorityServiceEntity.builder()
                .feesPersonalAccounts(feesPersonalAccountsEntity)
                .name(priorityService.getName())
                .code(priorityService.getCode())
                .chargingTriggerInfo(priorityService.getChargingTriggerInfo())
                .build();

        priorityServiceEntity.setPrices(convertListOfPricesToListOfPricesEntity(priorityService.getPrices(), priorityServiceEntity));
        priorityServiceEntity.setMinimum(MinimumToMinimumEntityMapper.convertWithPriorityService(priorityService.getMinimum(), priorityServiceEntity));
        priorityServiceEntity.setMaximum(MaximumToMaximumEntityMapper.convertWithPriorityService(priorityService.getMaximum(), priorityServiceEntity));

        return priorityServiceEntity;
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices, final PriorityServiceEntity priorityServiceEntity) {
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithPriorityService(price, priorityServiceEntity)).collect(Collectors.toList());
    }

}
