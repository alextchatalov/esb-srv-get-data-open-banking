package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "service_bundle")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class ServiceBundleEntity {

    private String name;
    @OneToMany(mappedBy = "serviceBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<ServiceFromServiceBundleEntity> services;

    @OneToMany(mappedBy = "serviceBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<PriceEntity> prices;

    private MinimumEntity minimum;
    private MaximumEntity maximum;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PersonalAccountEntity personalAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BusinessAccountEntity businessAccount;
}
