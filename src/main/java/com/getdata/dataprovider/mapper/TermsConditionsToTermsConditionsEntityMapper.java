package com.getdata.dataprovider.mapper;

import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class TermsConditionsToTermsConditionsEntityMapper implements Converter<TermsConditions, TermsConditionsEntity> {

    @Override
    @NonNull
    public TermsConditionsEntity convert(final TermsConditions termsConditions) {

        String elegibilityCriteriaInfo = termsConditions.getElegibilityCriteriaInfo() != null && termsConditions.getElegibilityCriteriaInfo().length() >= 255 ?
                termsConditions.getElegibilityCriteriaInfo().substring(0, 254) :
                termsConditions.getElegibilityCriteriaInfo();

        String closingProcessInfo = termsConditions.getClosingProcessInfo() != null && termsConditions.getClosingProcessInfo().length() >= 255 ?
                termsConditions.getClosingProcessInfo().substring(0, 254) :
                termsConditions.getClosingProcessInfo();

        TermsConditionsEntity termsConditionsEntity = TermsConditionsEntity.builder()
                .elegibilityCriteriaInfo(elegibilityCriteriaInfo)
                .closingProcessInfo(closingProcessInfo)
                .build();

        termsConditionsEntity.setMinimumBalance(MinimumBalanceToMinimumBalanceEntityMapper.convert(termsConditions.getMinimumBalance(), termsConditionsEntity));

        return termsConditionsEntity;
    }

}
