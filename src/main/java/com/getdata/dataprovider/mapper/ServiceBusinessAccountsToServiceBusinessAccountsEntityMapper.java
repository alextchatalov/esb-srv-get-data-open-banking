package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper {

    @NonNull
    public static ServiceBusinessAccountsEntity convert(final ServiceBusinessAccounts serviceBusinessAccounts) {

        String chargingTriggerInfo = serviceBusinessAccounts.getChargingTriggerInfo() != null && serviceBusinessAccounts.getChargingTriggerInfo().length() >= 255 ?
                serviceBusinessAccounts.getChargingTriggerInfo().substring(0, 254) :
                serviceBusinessAccounts.getChargingTriggerInfo();

        return ServiceBusinessAccountsEntity.builder()
                .name(serviceBusinessAccounts.getName())
                .code(serviceBusinessAccounts.getCode())
                .chargingTriggerInfo(chargingTriggerInfo)
                .prices(convertListOfPricesToListOfPricesEntity(serviceBusinessAccounts.getPrices()))
                .minimum(MinimumToMinimumEntityMapper.convert(serviceBusinessAccounts.getMinimum()))
                .maximum(MaximumToMaximumEntityMapper.convert(serviceBusinessAccounts.getMaximum()))
                .eventLimitQuantity(serviceBusinessAccounts.getEventLimitQuantity())
                .freeEventQuantity(serviceBusinessAccounts.getFreeEventQuantity())
                .build();
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(PriceToPriceEntityMapper::convert).collect(Collectors.toList());

    }

}
