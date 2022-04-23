package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
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
    public static ServiceBusinessAccountsEntity convert(final ServiceBusinessAccounts serviceBusinessAccounts, final FeesBusinessAccountsEntity feesBusinessAccountsEntity) {

        final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity = ServiceBusinessAccountsEntity.builder()
                .feesBusinessAccounts(feesBusinessAccountsEntity)
                .name(serviceBusinessAccounts.getName())
                .code(serviceBusinessAccounts.getCode())
                .chargingTriggerInfo(serviceBusinessAccounts.getChargingTriggerInfo())
                .eventLimitQuantity(serviceBusinessAccounts.getEventLimitQuantity())
                .freeEventQuantity(serviceBusinessAccounts.getFreeEventQuantity())
                .build();

        serviceBusinessAccountsEntity.setPrices(convertListOfPricesToListOfPricesEntity(serviceBusinessAccounts.getPrices(), serviceBusinessAccountsEntity));
        serviceBusinessAccountsEntity.setMinimum(MinimumToMinimumEntityMapper.convertWithServiceBusinessAccounts(serviceBusinessAccounts.getMinimum(), serviceBusinessAccountsEntity));
        serviceBusinessAccountsEntity.setMaximum(MaximumToMaximumEntityMapper.convertWithServiceBusinessAccounts(serviceBusinessAccounts.getMaximum(), serviceBusinessAccountsEntity));

        return serviceBusinessAccountsEntity;
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices, final ServiceBusinessAccountsEntity serviceBusinessAccountsEntity) {
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithServiceBusinessAccounts(price, serviceBusinessAccountsEntity)).collect(Collectors.toList());

    }

}
