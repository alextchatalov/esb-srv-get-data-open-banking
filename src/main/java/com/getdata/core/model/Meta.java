package com.getdata.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
public class Meta {
    private int totalRecords;
    private int totalPages;
}
