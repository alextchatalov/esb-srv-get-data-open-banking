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
public class ServiceBusinessAccountsEntity {
    
    public String name;
    public String code;
    public String chargingTriggerInfo;
    public List<PriceEntity> prices;
    public MinimumEntity minimum;
    public MaximumEntity maximum;
    public String eventLimitQuantity;
    public String freeEventQuantity;
}
