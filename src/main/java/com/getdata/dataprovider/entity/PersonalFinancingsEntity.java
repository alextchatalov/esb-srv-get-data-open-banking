package com.getdata.dataprovider.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "personal_financings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class PersonalFinancingsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeFinancings type;

    @OneToOne(mappedBy = "personalFinancings", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private FeesPersonalFinancingsEntity fees;

    @OneToMany(mappedBy = "personalFinancings", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<InterestRateEntity> interestRates;

    @OneToMany(mappedBy = "personalFinancings", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<RequiredWarrantiesEntity> requiredWarranties;

    @Column(length = 2000)
    private String termsConditions;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CompanyEntity company;
}
