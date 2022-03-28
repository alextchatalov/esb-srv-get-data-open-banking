package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
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
    public static FeesPersonalAccountsEntity convert(final FeesPersonalAccounts feesPersonalAccounts, final PersonalAccountEntity personalAccountEntity) {

        final FeesPersonalAccountsEntity feesPersonalAccountsEntity = FeesPersonalAccountsEntity.builder()
                .personalAccount(personalAccountEntity)
                .build();

        feesPersonalAccountsEntity.setPriorityServices((convertListOfPriorityServicesToListOfPriorityServicesEntity(feesPersonalAccounts.getPriorityServices(), feesPersonalAccountsEntity)));
        feesPersonalAccountsEntity.setOtherServices(convertListOfOtherServicesToListOfOtherServicesEntity(feesPersonalAccounts.getOtherServices(), feesPersonalAccountsEntity));

        return feesPersonalAccountsEntity;
    }

    private static List<PriorityServiceEntity> convertListOfPriorityServicesToListOfPriorityServicesEntity(final List<PriorityService> priorityServices, final FeesPersonalAccountsEntity feesPersonalAccountsEntity) {
        return priorityServices.stream().map(priorityService -> PriorityServiceToPriorityServiceEntityMapper.convert(priorityService, feesPersonalAccountsEntity)).collect(Collectors.toList());
    }

    private static List<OtherServiceEntity> convertListOfOtherServicesToListOfOtherServicesEntity(final List<OtherService> otherServices, final FeesPersonalAccountsEntity feesPersonalAccountsEntity) {
        return otherServices.stream().map(others -> OtherServiceToOtherServiceEntityMapper.convert(others, feesPersonalAccountsEntity)).collect(Collectors.toList());
    }

}
