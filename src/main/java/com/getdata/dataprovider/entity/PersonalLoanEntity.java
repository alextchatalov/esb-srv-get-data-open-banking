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
@Table(name = "personal_loan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class PersonalLoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeLoan type;

    @OneToOne(mappedBy = "personalLoan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private FeesLoanEntity fees;

    @OneToMany(mappedBy = "personalLoan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<InterestRateEntity> interestRates;

    @OneToMany(mappedBy = "personalLoan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<RequiredWarrantiesEntity> requiredWarranties;

    private String termsConditions;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CompanyEntity company;
}
