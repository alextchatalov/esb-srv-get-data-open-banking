package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class RequiredWarrantiesToRequiredWarrantiesEntityMapper {

    @NonNull
    public static RequiredWarrantiesEntity convert(final String requiredWarranties, final PersonalLoanEntity personalLoanEntity) {

        return RequiredWarrantiesEntity.builder()
                .personalLoan(personalLoanEntity)
                .warranties(requiredWarranties)
                .build();
    }
}
