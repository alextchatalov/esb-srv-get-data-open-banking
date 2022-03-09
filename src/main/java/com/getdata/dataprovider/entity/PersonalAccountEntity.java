package com.getdata.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class PersonalAccountEntity {
    private String type;
    private FeesPersonalAccountsEntity fees;
    private List<ServiceBundleEntity> serviceBundles;
    private List<String> openingClosingChannels;
    private String additionalInfo;
    private List<String> transactionMethods;
    private TermsConditionsEntity termsConditions;
    private List<IncomeRateEntity> incomeRate;
}
