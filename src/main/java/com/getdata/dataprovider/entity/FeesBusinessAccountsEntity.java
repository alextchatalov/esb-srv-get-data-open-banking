package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "fees_business_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class FeesBusinessAccountsEntity {

    public List<ServiceBusinessAccountsEntity> services;

    @OneToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BusinessAccountEntity businessAccount;
}
