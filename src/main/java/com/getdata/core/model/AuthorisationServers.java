package com.getdata.core.model;

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
public class AuthorisationServers {

    private String customerFriendlyName;
    private String customerFriendlyLogoUri;
    private List<ApiResource> apiResources;

}
