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
import javax.persistence.Column;
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
@Table(name = "priority_service")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class PriorityServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String code;
    @Column(length = 2000)
    private String chargingTriggerInfo;
    @OneToMany(mappedBy = "priorityService", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<PriceEntity> prices;
    @OneToOne(mappedBy = "priorityService", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private MinimumEntity minimum;
    @OneToOne(mappedBy = "priorityService", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private MaximumEntity maximum;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FeesPersonalAccountsEntity feesPersonalAccounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FeesLoanEntity feesLoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FeesPersonalFinancingsEntity feesPersonalFinancings;
}
