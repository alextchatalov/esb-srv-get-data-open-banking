package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Brand;
import com.getdata.core.model.Company;
import com.getdata.core.model.Data;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.DataEntity;
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
public class DataToDataEntityMapper implements Converter<Data, DataEntity> {

    private final CompanyToCompanyEntityMapper companyToCompanyEntityMapper;

    @Override
    @NonNull
    public DataEntity convert(final Data data) {
        return DataEntity.builder()
                .brand(convertBrandToBrandEntity(data.getBrand()))
                .build();
    }

    private BrandEntity convertBrandToBrandEntity(final Brand brand) {
        return BrandEntity.builder()
                .name(brand.getName())
                .companies(convertListOfCompaniesToListOfCompaniesEntity(brand.getCompanies()))
                .build();
    }

    private List<CompanyEntity> convertListOfCompaniesToListOfCompaniesEntity(final List<Company> companies) {
        return companies.stream().map(companyToCompanyEntityMapper::convert).collect(Collectors.toList());
    }
}
