package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class ServiceBundleToServiceBundleEntityMapper {

    @NonNull
    public static ServiceBundleEntity convert(final ServiceBundle serviceBundle) {
        return ServiceBundleEntity.builder()
                .name(serviceBundle.getName())
                .services(convertListOfServicesToListOfServicesEntity(serviceBundle.getServices()))
                .prices(convertListOfPricesToListOfPricesEntity(serviceBundle.getPrices()))
                .minimum(MinimumToMinimumEntityMapper.convert(serviceBundle.getMinimum()))
                .maximum(MaximumToMaximumEntityMapper.convert(serviceBundle.getMaximum()))
                .build();
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(PriceToPriceEntityMapper::convert).collect(Collectors.toList());
    }

    private static List<ServiceFromServiceBundleEntity> convertListOfServicesToListOfServicesEntity(final List<ServiceFromServiceBundle> serviceFromServicePersonalAccounts) {
        return serviceFromServicePersonalAccounts.stream().map(ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

}
