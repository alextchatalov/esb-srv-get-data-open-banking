package com.getdata.dataprovider.mapper;

import com.getdata.core.model.BusinessLoan;
import com.getdata.core.model.InterestRate;
import com.getdata.dataprovider.entity.TypeLoan;
import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class BusinessLoanToBusinessLoanEntityMapper {

    @NonNull
    public static BusinessLoanEntity convert(final BusinessLoan businessLoan, final CompanyEntity companyEntity) {

        final String termsConditions = businessLoan.getTermsConditions() != null && businessLoan.getTermsConditions().length() >= 255 ?
                businessLoan.getTermsConditions().substring(0, 254) :
                businessLoan.getTermsConditions();

        final BusinessLoanEntity businessLoanEntity = BusinessLoanEntity.builder()
                .company(companyEntity)
                .type(TypeLoan.valueOf(businessLoan.getType()))
                .termsConditions(termsConditions)
                .build();

        businessLoanEntity.setFees(FeesLoanToFeesLoanEntityMapper.convert(businessLoan.getFees(), businessLoanEntity));
        businessLoanEntity.setInterestRates(convertInterestRateListToInterestRateEntityList(businessLoan.getInterestRates(), businessLoanEntity));
        businessLoanEntity.setRequiredWarranties(convertRequiredWarrantiesListToRequiredWarrantiesEntityList(businessLoan.getRequiredWarranties(), businessLoanEntity));

        return businessLoanEntity;
    }

    private static List<InterestRateEntity> convertInterestRateListToInterestRateEntityList(final List<InterestRate> interestRates, final BusinessLoanEntity businessLoanEntity) {
        if (interestRates != null && !interestRates.isEmpty()) {
            return interestRates.stream().map(interestRate -> InterestRateToInterestRateEntityMapper.convert(interestRate, businessLoanEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<RequiredWarrantiesEntity> convertRequiredWarrantiesListToRequiredWarrantiesEntityList(final List<String> requiredWarranties, final BusinessLoanEntity businessLoanEntity) {
        if (requiredWarranties != null && !requiredWarranties.isEmpty()) {
            return requiredWarranties.stream().map(warranties -> RequiredWarrantiesToRequiredWarrantiesEntityMapper.convert(warranties, businessLoanEntity)).collect(Collectors.toList());
        }
        return null;
    }

}
