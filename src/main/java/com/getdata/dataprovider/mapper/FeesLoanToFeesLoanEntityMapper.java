package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesLoan;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceLoans;
import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.FeesLoanEntity;
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
public class FeesLoanToFeesLoanEntityMapper {

    @NonNull
    public static FeesLoanEntity convert(final FeesLoan fees, final PersonalLoanEntity personalLoanEntity) {

        final FeesLoanEntity feesEntity = FeesLoanEntity.builder()
                .personalLoan(personalLoanEntity)
                .build();
        final List<PriorityServiceEntity> priorityServiceEntities = createPriorityServiceFromLoan(fees.getServices(), feesEntity);
        feesEntity.setServices(priorityServiceEntities);

        return feesEntity;
    }

    @NonNull
    public static FeesLoanEntity convert(final FeesLoan fees, final BusinessLoanEntity businessLoanEntity) {

        final FeesLoanEntity feesEntity = FeesLoanEntity.builder()
                .businessLoan(businessLoanEntity)
                .build();
        final List<PriorityServiceEntity> priorityServiceEntities = createPriorityServiceFromLoan(fees.getServices(), feesEntity);
        feesEntity.setServices(priorityServiceEntities);

        return feesEntity;
    }

    private static List<PriorityServiceEntity> createPriorityServiceFromLoan(final List<ServiceLoans> services, final FeesLoanEntity feesLoanEntity) {

        if (services == null) {
            return null;
        }

        final List<PriorityServiceEntity> priorityServiceEntities = new ArrayList<>();

        for (final ServiceLoans service : services) {

            final PriorityServiceEntity priorityServiceEntity = PriorityServiceEntity.builder()
                    .feesLoan(feesLoanEntity)
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
        if (prices == null || prices.isEmpty()) {
            return null;
        }
        return prices.stream().map(price -> PriceToPriceEntityMapper.convertWithPriorityService(price, priorityServiceEntity)).collect(Collectors.toList());
    }

}
