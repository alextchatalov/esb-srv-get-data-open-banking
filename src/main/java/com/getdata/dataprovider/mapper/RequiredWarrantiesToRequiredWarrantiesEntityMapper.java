package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class RequiredWarrantiesToRequiredWarrantiesEntityMapper {

    @NonNull
    public static RequiredWarrantiesEntity convertPersonal(final String requiredWarranties, final PersonalLoanEntity personalLoanEntity) {

        return convert(RequiredWarrantiesEntity.builder()
                .personalLoan(personalLoanEntity), requiredWarranties);
    }

    @NonNull
    public static RequiredWarrantiesEntity convertBusiness(final String requiredWarranties, final BusinessLoanEntity businessLoanEntity) {

        return convert(RequiredWarrantiesEntity.builder()
                .businessLoan(businessLoanEntity), requiredWarranties);
    }

    private static RequiredWarrantiesEntity convert(final RequiredWarrantiesEntity.RequiredWarrantiesEntityBuilder businessLoanEntity, final String requiredWarranties) {
        return businessLoanEntity
                .warranties(requiredWarranties)
                .build();
    }
}
