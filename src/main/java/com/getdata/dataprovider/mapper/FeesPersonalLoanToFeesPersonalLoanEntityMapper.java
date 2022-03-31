package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesPersonalLoan;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServicePersonalLoans;
import com.getdata.dataprovider.entity.FeesPersonalLoanEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class FeesPersonalLoanToFeesPersonalLoanEntityMapper {

    @NonNull
    public static FeesPersonalLoanEntity convert(final FeesPersonalLoan fees, final PersonalLoanEntity personalLoanEntity) {

        final FeesPersonalLoanEntity feesEntity = FeesPersonalLoanEntity.builder()
                .personalLoan(personalLoanEntity)
                .build();
        final List<PriorityServiceEntity> priorityServiceEntities = createPriorityServiceFromServicePersonalLoan(fees.getServices());
        feesEntity.setServices(priorityServiceEntities);

        return feesEntity;
    }

    private static List<PriorityServiceEntity> createPriorityServiceFromServicePersonalLoan(final List<ServicePersonalLoans> services) {

        if (services == null) {
            return null;
        }

        final List<PriorityServiceEntity> priorityServiceEntities = new ArrayList<>();

        for (final ServicePersonalLoans service : services) {

            final PriorityServiceEntity priorityServiceEntity = PriorityServiceEntity.builder()
                    .name(service.getName())
                    .code(service.getCode())
                    .chargingTriggerInfo(service.getChargingTriggerInfo())
                    .build();

            priorityServiceEntity.setPrices(convertListOfPricesToListOfPricesEntity(service.getPrices(), priorityServiceEntity));
            priorityServiceEntity.setMaximum(MaximumToMaximumEntityMapper.convertWithPriorityService(service.getMaximum(), priorityServiceEntity));
            priorityServiceEntity.setMinimum(MinimumToMinimumEntityMapper.convertWithPriorityService(service.getMinimum(), priorityServiceEntity));

            priorityServiceEntities.add(priorityServiceEntity);
        }
        return priorityServiceEntities;
    }

    private static List<PriceEntity> convertListOfPricesToListOfPricesEntity(final List<Price> prices, final PriorityServiceEntity priorityServiceEntity) {
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithPriorityService(price, priorityServiceEntity)).collect(Collectors.toList());
    }

}
