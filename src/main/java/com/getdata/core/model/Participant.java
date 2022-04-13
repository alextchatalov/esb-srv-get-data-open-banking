package com.getdata.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class Participant {

    private String organisationId;
    private ParticipantStatus status;
    private String organisationName;
    private String customerFriendlyName;
    private String customerFriendlyLogoUri;

    @Builder.Default
    @Setter
    private List<ApiResource> apiResources = new ArrayList<>();

    public boolean isActive() {
        return ParticipantStatus.ACTIVE.equals(this.status);
    }

    public List<ApiResource> getApiProductsServices() {

        return this.getApiResources().stream()
                .filter(apiResource -> "products-services".equals(apiResource.getApiFamilyType()) && this.isActive())
                .collect(Collectors.toList());
    }
}
