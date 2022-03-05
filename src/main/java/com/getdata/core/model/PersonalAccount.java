package com.getdata.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalAccount {
    private String type;
    private FeesPersonalAccounts fees;
    private List<ServiceBundle> serviceBundles;
    private List<String> openingClosingChannels;
    private String additionalInfo;
    private List<String> transactionMethods;
    private TermsConditions termsConditions;
    private List<IncomeRate> incomeRate;
}
