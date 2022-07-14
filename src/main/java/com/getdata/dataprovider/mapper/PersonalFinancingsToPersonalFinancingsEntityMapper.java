package com.getdata.dataprovider.mapper;

import com.getdata.core.model.InterestRate;
import com.getdata.core.model.PersonalFinancings;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.PersonalFinancingsEntity;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import com.getdata.dataprovider.entity.TypeFinancings;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class PersonalFinancingsToPersonalFinancingsEntityMapper {

    @NonNull
    public static PersonalFinancingsEntity convert(final PersonalFinancings personalFinancings, final CompanyEntity companyEntity) {
        TypeFinancings typeFinancings;

        try {
            typeFinancings = TypeFinancings.valueOf(personalFinancings.getType());
        } catch (Exception e) {
            typeFinancings = TypeFinancings.NAO_IDENTIFICADO;
        }

        final PersonalFinancingsEntity personalFinancingsEntity = PersonalFinancingsEntity.builder()
                .company(companyEntity)
                .type(typeFinancings)
                .termsConditions(personalFinancings.getTermsConditions())
                .build();

        personalFinancingsEntity.setFees(FeesPersonalFinancingsToFeesPersonalFinancingsEntityMapper.convert(personalFinancings.getFees(), personalFinancingsEntity));
        personalFinancingsEntity.setInterestRates(convertInterestRateListToInterestRateEntityList(personalFinancings.getInterestRates(), personalFinancingsEntity));
        personalFinancingsEntity.setRequiredWarranties(convertRequiredWarrantiesListToRequiredWarrantiesEntityList(personalFinancings.getRequiredWarranties(), personalFinancingsEntity));

        return personalFinancingsEntity;
    }

    private static List<RequiredWarrantiesEntity> convertRequiredWarrantiesListToRequiredWarrantiesEntityList(final List<String> requiredWarranties, final PersonalFinancingsEntity personalFinancingsEntity) {
        if (requiredWarranties != null && !requiredWarranties.isEmpty()) {
            return requiredWarranties.stream().map(warranties -> RequiredWarrantiesToRequiredWarrantiesEntityMapper.convert(warranties, personalFinancingsEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<InterestRateEntity> convertInterestRateListToInterestRateEntityList(final List<InterestRate> interestRates, final PersonalFinancingsEntity personalFinancingsEntity) {
        if (interestRates != null && !interestRates.isEmpty()) {
            return interestRates.stream().map(interestRate -> InterestRateToInterestRateEntityMapper.convert(interestRate, personalFinancingsEntity)).collect(Collectors.toList());
        }
        return null;
    }

}
