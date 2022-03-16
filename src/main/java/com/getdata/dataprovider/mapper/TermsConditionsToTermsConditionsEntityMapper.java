package com.getdata.dataprovider.mapper;

import com.getdata.core.model.TermsConditions;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class TermsConditionsToTermsConditionsEntityMapper {

    @NonNull
    public static TermsConditionsEntity convert(final TermsConditions termsConditions) {

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
