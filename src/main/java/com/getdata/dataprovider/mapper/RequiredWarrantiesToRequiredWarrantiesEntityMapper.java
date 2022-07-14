package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.PersonalFinancingsEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.RequiredWarranties;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class RequiredWarrantiesToRequiredWarrantiesEntityMapper {

    @NonNull
    public static RequiredWarrantiesEntity convert(final String requiredWarranties, final PersonalLoanEntity personalLoanEntity) {

        return convertData(RequiredWarrantiesEntity.builder()
                .personalLoan(personalLoanEntity), requiredWarranties);
    }

    @NonNull
    public static RequiredWarrantiesEntity convert(final String requiredWarranties, final BusinessLoanEntity businessLoanEntity) {

        return convertData(RequiredWarrantiesEntity.builder()
                .businessLoan(businessLoanEntity), requiredWarranties);
    }

    @NonNull
    public static RequiredWarrantiesEntity convert(final String requiredWarranties, final PersonalFinancingsEntity personalFinancings) {

        return convertData(RequiredWarrantiesEntity.builder()
                .personalFinancings(personalFinancings), requiredWarranties);
    }

    private static RequiredWarrantiesEntity convertData(final RequiredWarrantiesEntity.RequiredWarrantiesEntityBuilder requiredWarrantiesEntityBuilder, final String requiredWarranties) {
        return requiredWarrantiesEntityBuilder
                .warranties(RequiredWarranties.get(requiredWarranties))
                .build();
    }
}
