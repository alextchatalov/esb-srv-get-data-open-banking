package com.getdata.dataprovider.mapper;

import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.BusinessLoan;
import com.getdata.core.model.Company;
import com.getdata.core.model.PersonalAccount;
import com.getdata.core.model.PersonalFinancings;
import com.getdata.core.model.PersonalLoan;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.PersonalFinancingsEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class CompanyToCompanyEntityMapper {


    public static CompanyEntity convert(final Company company, final BrandEntity brandEntity) {
        final CompanyEntity companyEntity = CompanyEntity.builder()
                .cnpjNumber(company.getCnpjNumber())
                .brand(brandEntity)
                .name(company.getName())
                .urlComplementaryList(company.getUrlComplementaryList())
                .build();

        final List<PersonalAccountEntity> personalAccountEntities = convertPersonalAccountsListToPersonalAccountEntityList(company.getPersonalAccounts(), companyEntity);
        final List<BusinessAccountEntity> businessAccountEntities = convertBusinessAccountListToBusinessAccountEntityList(company.getBusinessAccounts(), companyEntity);
        final List<PersonalLoanEntity> personalLoanEntities = convertPersonalLoansListToPersonalLoansEntityList(company.getPersonalLoans(), companyEntity);
        final List<BusinessLoanEntity> businessLoanEntities = convertBusinessLoansListToBusinessLoansEntityList(company.getBusinessLoans(), companyEntity);
        final List<PersonalFinancingsEntity> personalFinancingsEntities = convertPersonalFinancingsListToPersonalFinancingsEntityList(company.getPersonalFinancings(), companyEntity);
        companyEntity.setPersonalAccounts(personalAccountEntities);
        companyEntity.setBusinessAccounts(businessAccountEntities);
        companyEntity.setPersonalLoans(personalLoanEntities);
        companyEntity.setBusinessLoans(businessLoanEntities);
        companyEntity.setPersonalFinancings(personalFinancingsEntities);

        return companyEntity;
    }

    private static List<PersonalAccountEntity> convertPersonalAccountsListToPersonalAccountEntityList(final List<PersonalAccount> personalAccounts, final CompanyEntity companyEntity) {
        if (personalAccounts != null && !personalAccounts.isEmpty()) {
            return personalAccounts.stream().map(personalAccount -> PersonalAccountsToPersonalAccountEntityMapper.convert(personalAccount, companyEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<BusinessAccountEntity> convertBusinessAccountListToBusinessAccountEntityList(final List<BusinessAccount> businessAccounts, final CompanyEntity companyEntity) {
        if (businessAccounts != null && !businessAccounts.isEmpty()) {
            return businessAccounts.stream().map(business -> BusinessAccountToBusinessAccountEntityMapper.convert(business, companyEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<PersonalLoanEntity> convertPersonalLoansListToPersonalLoansEntityList(final List<PersonalLoan> personalLoans, final CompanyEntity companyEntity) {
        if (personalLoans != null && !personalLoans.isEmpty()) {
            return personalLoans.stream().map(personalLoan -> PersonalLoanToPersonalLoanEntityMapper.convert(personalLoan, companyEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<BusinessLoanEntity> convertBusinessLoansListToBusinessLoansEntityList(final List<BusinessLoan> businessLoans, final CompanyEntity companyEntity) {
        if (businessLoans != null && !businessLoans.isEmpty()) {
            return businessLoans.stream().map(businessLoan -> BusinessLoanToBusinessLoanEntityMapper.convert(businessLoan, companyEntity)).collect(Collectors.toList());
        }
        return null;
    }

    private static List<PersonalFinancingsEntity> convertPersonalFinancingsListToPersonalFinancingsEntityList(final List<PersonalFinancings> personalFinancings, final CompanyEntity companyEntity) {
        if (personalFinancings != null && !personalFinancings.isEmpty()) {
            return personalFinancings.stream().map(financings -> PersonalFinancingsToPersonalFinancingsEntityMapper.convert(financings, companyEntity)).collect(Collectors.toList());
        }
        return null;
    }
}