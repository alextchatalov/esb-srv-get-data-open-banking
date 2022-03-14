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
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class CompanyEntity {

    private String name;
    private String cnpjNumber;
    private String urlComplementaryList;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<PersonalAccountEntity> personalAccounts;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<BusinessAccountEntity> businessAccounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;
}
