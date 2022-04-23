package com.getdata.dataprovider.mapper;

import com.getdata.core.model.InterestRate;
import com.getdata.core.model.PersonalLoan;
import com.getdata.dataprovider.entity.TypeLoan;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class PersonalLoanToPersonalLoanEntityMapper {

    @NonNull
    public static PersonalLoanEntity convert(final PersonalLoan personalLoan, final CompanyEntity companyEntity) {

        final String termsConditions = personalLoan.getTermsConditions() != null && personalLoan.getTermsConditions().length() >= 255 ?
                personalLoan.getTermsConditions().substring(0, 254) :
                personalLoan.getTermsConditions();

        final PersonalLoanEntity personalLoanEntity = PersonalLoanEntity.builder()
                .company(companyEntity)
                .type(TypeLoan.get(personalLoan.getType()))
                .termsConditions(termsConditions)
                .build();

        personalLoanEntity.setFees(FeesLoanToFeesLoanEntityMapper.convert(personalLoan.getFees(), personalLoanEntity));
        personalLoanEntity.setInterestRates(convertInterestRateListToInterestRateEntityList(personalLoan.getInterestRates(), personalLoanEntity));
        personalLoanEntity.setRequiredWarranties(convertRequiredWarrantiesListToRequiredWarrantiesEntityList(personalLoan.getRequiredWarranties(), personalLoanEntity));

        return personalLoanEntity;
    }

    private static List<InterestRateEntity> convertInterestRateListToInterestRateEntityList(final List<InterestRate> interestRates, final PersonalLoanEntity personalLoanEntity) {
        if (interestRates != null && !interestRates.isEmpty()) {
            return interestRates.stream().map(interestRate -> InterestRateToInterestRateEntityMapper.convertWithPersonal(interestRate, personalLoanEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<RequiredWarrantiesEntity> convertRequiredWarrantiesListToRequiredWarrantiesEntityList(final List<String> requiredWarranties, final PersonalLoanEntity personalLoanEntity) {
        if (requiredWarranties != null && !requiredWarranties.isEmpty()) {
            return requiredWarranties.stream().map(warranties -> RequiredWarrantiesToRequiredWarrantiesEntityMapper.convertPersonal(warranties, personalLoanEntity)).collect(Collectors.toList());
        }
        return null;
    }

}
