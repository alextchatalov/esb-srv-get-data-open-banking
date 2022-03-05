package com.getdata.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriorityService {
    private String name;
    private String code;
    private String chargingTriggerInfo;
    private List<Price> prices;
    private Minimum minimum;
    private Maximum maximum;
}
