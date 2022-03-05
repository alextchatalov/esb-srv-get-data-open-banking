package com.getdata.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessAccount {
    private String type;
    private FeesBusinessAccounts fees;
    private List<ServiceBundle> serviceBundles;
    private List<String> openingClosingChannels;
    private String additionalInfo;
    private List<String> transactionMethods;
    private TermsConditions termsConditions;
    private IncomeRate incomeRate;
}
