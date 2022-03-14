package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.TermsConditionsEntity;
import com.getdata.core.model.TermsConditions;
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

    private final MinimumBalanceToMinimumBalanceEntityMapper minimumBalanceToMinimumBalanceEntityMapper;

    @Override
    @NonNull
    public TermsConditionsEntity convert(final TermsConditions termsConditions) {
        return TermsConditionsEntity.builder()
                .minimumBalance(minimumBalanceToMinimumBalanceEntityMapper.convert(termsConditions.getMinimumBalance()))
                .elegibilityCriteriaInfo(termsConditions.getElegibilityCriteriaInfo())
                .closingProcessInfo(termsConditions.getClosingProcessInfo())
                .build();
    }

}
