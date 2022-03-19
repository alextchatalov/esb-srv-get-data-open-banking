package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBundle;
import com.getdata.core.model.ServiceFromServiceBundle;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
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
    public static ServiceBundleEntity convertWithPersonalAccounts(final ServiceBundle serviceBundle, final PersonalAccountEntity personalAccountEntity) {
        return convert(serviceBundle, personalAccountEntity, null);
    }

    @NonNull
    public static ServiceBundleEntity convertWithBusinessAccounts(final ServiceBundle serviceBundle, final BusinessAccountEntity businessAccountEntity) {
        return convert(serviceBundle, null, businessAccountEntity);
    }

    private static ServiceBundleEntity convert(final ServiceBundle serviceBundle, final PersonalAccountEntity personalAccountEntity, final BusinessAccountEntity businessAccountEntity) {

        final ServiceBundleEntity serviceBundleEntity = ServiceBundleEntity.builder()
                .personalAccount(personalAccountEntity)
                .businessAccount(businessAccountEntity)
                .name(serviceBundle.getName())
                .build();

        serviceBundleEntity.setServices(convertListOfServicesToListOfServicesEntity(serviceBundle.getServices(), serviceBundleEntity));
        serviceBundleEntity.setPrices(convertListOfPricesToListOfPricesEntity(serviceBundle.getPrices(), serviceBundleEntity));
        serviceBundleEntity.setMinimum(MinimumToMinimumEntityMapper.convertWithServiceBundle(serviceBundle.getMinimum(), serviceBundleEntity));
        serviceBundleEntity.setMaximum(MaximumToMaximumEntityMapper.convertWithServiceBundle(serviceBundle.getMaximum(), serviceBundleEntity));

        return serviceBundleEntity;
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices, final ServiceBundleEntity serviceBundleEntity) {
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithServiceBundle(price, serviceBundleEntity)).collect(Collectors.toList());
    }

    private static List<ServiceFromServiceBundleEntity> convertListOfServicesToListOfServicesEntity(final List<ServiceFromServiceBundle> serviceFromServicePersonalAccounts, final ServiceBundleEntity serviceBundleEntity) {
        return serviceFromServicePersonalAccounts.stream().map(service -> ServiceFromServiceBundleToServiceFromServiceBundleEntityMapper.convert(service, serviceBundleEntity)).collect(Collectors.toList());
    }

}
