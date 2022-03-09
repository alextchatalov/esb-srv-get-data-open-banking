package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
@SuperBuilder
public class BusinessAccountEntity {
    private String type;
    private FeesBusinessAccountsEntity fees;
    private List<ServiceBundleEntity> serviceBundles;
    private List<String> openingClosingChannels;
    private String additionalInfo;
    private List<String> transactionMethods;
    private TermsConditionsEntity termsConditions;
    private IncomeRateEntity incomeRate;
}
