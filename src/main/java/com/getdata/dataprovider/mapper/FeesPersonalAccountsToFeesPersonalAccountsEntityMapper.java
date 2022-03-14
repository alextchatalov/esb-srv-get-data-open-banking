package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PriorityService;
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
public class FeesPersonalAccountsToFeesPersonalAccountsEntityMapper implements Converter<FeesPersonalAccounts, FeesPersonalAccountsEntity> {

    private final PriorityServiceToPriorityServiceEntityMapper priorityServiceToPriorityServiceEntityMapper;
    private final OtherServiceToOtherServiceEntityMapper otherServiceToOtherServiceEntityMapper;

    @Override
    @NonNull
    public FeesPersonalAccountsEntity convert(final FeesPersonalAccounts feesPersonalAccounts) {
        return FeesPersonalAccountsEntity.builder()
                .priorityServices(convertListOfPriorityServicesToListOfPriorityServicesEntity(feesPersonalAccounts.getPriorityServices()))
                .otherServices(convertListOfOtherServicesToListOfOtherServicesEntity(feesPersonalAccounts.getOtherServices()))
                .build();
    }

    private List<PriorityServiceEntity> convertListOfPriorityServicesToListOfPriorityServicesEntity(final List<PriorityService> priorityServices) {
        return priorityServices.stream().map(priorityServiceToPriorityServiceEntityMapper::convert).collect(Collectors.toList());
    }

    private List<OtherServiceEntity> convertListOfOtherServicesToListOfOtherServicesEntity(final List<OtherService> otherServices) {
        return otherServices.stream().map(otherServiceToOtherServiceEntityMapper::convert).collect(Collectors.toList());
    }

}
