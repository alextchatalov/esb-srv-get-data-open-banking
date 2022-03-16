package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class FeesPersonalAccountsToFeesPersonalAccountsEntityMapper {

    @NonNull
    public static FeesPersonalAccountsEntity convert(final FeesPersonalAccounts feesPersonalAccounts) {
        return FeesPersonalAccountsEntity.builder()
                .priorityServices(convertListOfPriorityServicesToListOfPriorityServicesEntity(feesPersonalAccounts.getPriorityServices()))
                .otherServices(convertListOfOtherServicesToListOfOtherServicesEntity(feesPersonalAccounts.getOtherServices()))
                .build();
    }

    private static List<PriorityServiceEntity> convertListOfPriorityServicesToListOfPriorityServicesEntity(final List<PriorityService> priorityServices) {
        return priorityServices.stream().map(PriorityServiceToPriorityServiceEntityMapper::convert).collect(Collectors.toList());
    }

    private static List<OtherServiceEntity> convertListOfOtherServicesToListOfOtherServicesEntity(final List<OtherService> otherServices) {
        return otherServices.stream().map(OtherServiceToOtherServiceEntityMapper::convert).collect(Collectors.toList());
    }

}
