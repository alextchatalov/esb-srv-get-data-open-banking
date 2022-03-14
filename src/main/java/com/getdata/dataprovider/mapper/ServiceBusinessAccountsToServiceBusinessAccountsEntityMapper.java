package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBusinessAccounts;
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
public class ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper implements Converter<ServiceBusinessAccounts, ServiceBusinessAccountsEntity> {

    private final PriceToPriceEntityMapper priceToPriceEntityMapper;
    private final MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;
    private final MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;

    @Override
    @NonNull
    public ServiceBusinessAccountsEntity convert(final ServiceBusinessAccounts serviceBusinessAccounts) {
        return ServiceBusinessAccountsEntity.builder()
                .name(serviceBusinessAccounts.getName())
                .code(serviceBusinessAccounts.getCode())
                .chargingTriggerInfo(serviceBusinessAccounts.getChargingTriggerInfo())
                .prices(convertListOfPricesToListOfPricesEntity(serviceBusinessAccounts.getPrices()))
                .minimum(minimumToMinimumEntityMapper.convert(serviceBusinessAccounts.getMinimum()))
                .maximum(maximumToMaximumEntityMapper.convert(serviceBusinessAccounts.getMaximum()))
                .eventLimitQuantity(serviceBusinessAccounts.getEventLimitQuantity())
                .freeEventQuantity(serviceBusinessAccounts.getFreeEventQuantity())
                .build();
    }

    private List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(priceToPriceEntityMapper::convert).collect(Collectors.toList());

    }

}
