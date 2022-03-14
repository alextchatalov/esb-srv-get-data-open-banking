package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBundleEntity;
import com.getdata.dataprovider.entity.ServiceFromServiceBundleEntity;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceFromServiceBundle;
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
public class ServiceBundleToServiceBundleEntityMapper implements Converter<ServiceBundle, ServiceBundleEntity> {

    private final PriceToPriceEntityMapper priceToPriceEntityMapper;
    private final MinimumToMinimumEntityMapper minimumToMinimumEntityMapper;
    private final MaximumToMaximumEntityMapper maximumToMaximumEntityMapper;
    private final ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper serviceFromServiceBundleToServiceFromServiceBundleEntityMapper;

    @Override
    @NonNull
    public ServiceBundleEntity convert(final ServiceBundle serviceBundle) {
        return ServiceBundleEntity.builder()
                .name(serviceBundle.getName())
                .services(convertListOfServicesToListOfServicesEntity(serviceBundle.getServices()))
                .prices(convertListOfPricesToListOfPricesEntity(serviceBundle.getPrices()))
                .minimum(minimumToMinimumEntityMapper.convert(serviceBundle.getMinimum()))
                .maximum(maximumToMaximumEntityMapper.convert(serviceBundle.getMaximum()))
                .build();
    }

    private List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices) {
        return prices.stream().map(priceToPriceEntityMapper::convert).collect(Collectors.toList());
    }

    private List<ServiceFromServiceBundleEntity> convertListOfServicesToListOfServicesEntity(final List<ServiceFromServiceBundle> serviceFromServicePersonalAccounts) {
        return serviceFromServicePersonalAccounts.stream().map(serviceFromServiceBundleToServiceFromServiceBundleEntityMapper::convert).collect(Collectors.toList());
    }

}
