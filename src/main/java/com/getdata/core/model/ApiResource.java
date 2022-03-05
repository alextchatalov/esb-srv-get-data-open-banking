package com.getdata.core.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
@EqualsAndHashCode
public class ApiResource {

    private String apiFamilyType;
    private String apiVersion;
    private List<String> apiEndpoint;
}
