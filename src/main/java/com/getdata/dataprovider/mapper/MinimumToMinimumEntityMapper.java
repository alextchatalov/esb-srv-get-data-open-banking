package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Minimum;
import com.getdata.dataprovider.entity.MinimumEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class MinimumToMinimumEntityMapper {

    @NonNull
    public static MinimumEntity convert(final Minimum minimum) {
        return MinimumEntity.builder()
                .value(minimum.getValue())
                .currency(minimum.getCurrency())
                .build();
    }
}
