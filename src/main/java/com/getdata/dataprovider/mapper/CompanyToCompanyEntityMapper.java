package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.core.model.BusinessAccount;
import com.getdata.core.model.Company;
import com.getdata.core.model.PersonalAccount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class CompanyToCompanyEntityMapper implements Converter<Company, CompanyEntity> {

    private final PersonalAccountsToPersonalAccountEntityMapper personalAccountsToPersonalAccountEntityMapper;
    private final BusinessAccountToBusinessAccountEntityMapper businessAccountToBusinessAccountEntityMapper;

    @Override
    @NonNull
    public CompanyEntity convert(final Company company) {
        return CompanyEntity.builder()
                .cnpjNumber(company.getCnpjNumber())
                .name(company.getName())
                .personalAccounts(convertPersonalAccountsListToPersonalAccountEntityList(company.getPersonalAccounts()))
                .businessAccounts(convertBusinessAccountListToBusinessAccountEntityList(company.getBusinessAccounts()))
                .urlComplementaryList(company.getUrlComplementaryList())
                .build();
    }

    private List<PersonalAccountEntity> convertPersonalAccountsListToPersonalAccountEntityList(final List<PersonalAccount> personalAccounts) {
        if (personalAccounts != null && !personalAccounts.isEmpty()) {
            return personalAccounts.stream().map(personalAccountsToPersonalAccountEntityMapper::convert).collect(Collectors.toList());
        }
        return null;
    }

    private List<BusinessAccountEntity> convertBusinessAccountListToBusinessAccountEntityList(final List<BusinessAccount> businessAccounts) {
        if (businessAccounts != null && !businessAccounts.isEmpty()) {
            return businessAccounts.stream().map(businessAccountToBusinessAccountEntityMapper::convert).collect(Collectors.toList());
        }
        return null;
    }
}
