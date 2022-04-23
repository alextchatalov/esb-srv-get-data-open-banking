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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "business_account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class BusinessAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeAccount type;

    @OneToOne(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private FeesBusinessAccountsEntity fees;

    @OneToMany(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<ServiceBundleEntity> serviceBundles;

    @OneToMany(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<OpeningClosingChannelsEntity> openingClosingChannels;

    private String additionalInfo;

    @OneToMany(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<TransactionMethodsEntity> transactionMethods;

    @OneToOne(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private TermsConditionsEntity termsConditions;

    @OneToOne(mappedBy = "businessAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private IncomeRateEntity incomeRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CompanyEntity company;
}
