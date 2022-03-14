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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "income_rate")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class IncomeRateEntity {

    private String savingAccount;
    private String prepaidPaymentAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PersonalAccountEntity personalAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BusinessAccountEntity businessAccount;
}
