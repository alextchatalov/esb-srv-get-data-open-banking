
package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesPersonalAccounts;
import com.getdata.core.model.FeesPersonalFinancings;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.PriorityService;
import com.getdata.dataprovider.entity.FeesPersonalAccountsEntity;
import com.getdata.dataprovider.entity.FeesPersonalFinancingsEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.PersonalFinancingsEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class FeesPersonalFinancingsToFeesPersonalFinancingsEntityMapper {

    @NonNull
    public static FeesPersonalFinancingsEntity convert(final FeesPersonalFinancings feesPersonalFinancings, final PersonalFinancingsEntity personalFinancingsEntity) {

        final FeesPersonalFinancingsEntity feesPersonalFinancingsEntity = FeesPersonalFinancingsEntity.builder()
                .personalFinancings(personalFinancingsEntity)
                .build();

        feesPersonalFinancingsEntity.setServices((convertListOfPriorityServicesToListOfPriorityServicesEntity(feesPersonalFinancings.getServices(), feesPersonalFinancingsEntity)));

        return feesPersonalFinancingsEntity;
    }

    private static List<PriorityServiceEntity> convertListOfPriorityServicesToListOfPriorityServicesEntity(final List<PriorityService> services, final FeesPersonalFinancingsEntity feesPersonalFinancingsEntity) {
        if (services == null || services.isEmpty()) {
            return null;
        }
        return services.stream().map(service -> PriorityServiceToPriorityServiceEntityMapper.convert(service, feesPersonalFinancingsEntity)).collect(Collectors.toList());
    }

}
