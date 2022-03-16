package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Maximum;
import com.getdata.dataprovider.entity.MaximumEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class MaximumToMaximumEntityMapper {

    @NonNull
    public static MaximumEntity convert(final Maximum maximum) {
        return MaximumEntity.builder()
                .value(maximum.getValue())
                .currency(maximum.getCurrency())
                .build();
    }
}
