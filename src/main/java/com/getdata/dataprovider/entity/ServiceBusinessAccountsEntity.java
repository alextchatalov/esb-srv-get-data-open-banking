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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "service_business_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class ServiceBusinessAccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String name;
    public String code;
    public String chargingTriggerInfo;

    @OneToMany(mappedBy = "serviceBusinessAccounts", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    public List<PriceEntity> prices;

    @OneToOne(mappedBy = "serviceBusinessAccounts", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    public MinimumEntity minimum;

    @OneToOne(mappedBy = "serviceBusinessAccounts", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    public MaximumEntity maximum;

    public String eventLimitQuantity;
    public String freeEventQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FeesBusinessAccountsEntity feesBusinessAccounts;
}
