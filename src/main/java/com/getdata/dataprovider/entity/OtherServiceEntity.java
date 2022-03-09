package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class OtherServiceEntity {
    private String name;
    private String code;
    private String chargingTriggerInfo;
    private List<PriceEntity> prices;
    private MinimumEntity minimum;
    private MaximumEntity maximum;
}
