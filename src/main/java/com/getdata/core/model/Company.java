package com.getdata.core.model;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class Company {
    private String name;
    private String cnpjNumber;
    private String urlComplementaryList;
    @JsonAlias("personalAccounts")
    private List<PersonalAccount> personalAccounts;
    @JsonAlias("businessAccounts")
    private List<BusinessAccount> businessAccounts;
    @JsonAlias("personalLoans")
    private List<PersonalLoan> personalLoans;
    @JsonAlias("businessLoans")
    private List<BusinessLoan> businessLoans;
}
