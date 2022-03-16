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

    @Override
    @NonNull
    public DataEntity convert(final Data data) {

        DataEntity dataEntity = DataEntity.builder().build();
        BrandEntity brandEntity = convertBrandToBrandEntity(data.getBrand(), dataEntity);
        dataEntity.setBrand(brandEntity);
        return dataEntity;
    }

    private BrandEntity convertBrandToBrandEntity(final Brand brand, DataEntity dataEntity) {
        BrandEntity brandEntity = BrandEntity.builder()
                .data(dataEntity)
                .name(brand.getName())
                .build();

        List<CompanyEntity> companyEntities = convertListOfCompaniesToListOfCompaniesEntity(brand.getCompanies(), brandEntity);
        brandEntity.setCompanies(companyEntities);
        return brandEntity;
    }

    private List<CompanyEntity> convertListOfCompaniesToListOfCompaniesEntity(final List<Company> companies, final BrandEntity brandEntity) {
        return companies.stream().map(company -> CompanyToCompanyEntityMapper.convert(company, brandEntity)).collect(Collectors.toList());
    }
}
