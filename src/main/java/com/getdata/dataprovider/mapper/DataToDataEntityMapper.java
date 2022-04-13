package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Brand;
import com.getdata.core.model.Company;
import com.getdata.core.model.Data;
import com.getdata.dataprovider.entity.BrandEntity;
import com.getdata.dataprovider.entity.CompanyEntity;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.entity.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
@Component
@AllArgsConstructor
public class DataToDataEntityMapper {

    @NonNull
    public static DataEntity convert(final Data data, final ParticipantEntity participantEntity) {

        final DataEntity dataEntity = DataEntity.builder().build();
        final BrandEntity brandEntity = convertBrandToBrandEntity(data.getBrand(), dataEntity, participantEntity);
        dataEntity.setBrand(brandEntity);
        return dataEntity;
    }

    private static BrandEntity convertBrandToBrandEntity(final Brand brand, final DataEntity dataEntity, final ParticipantEntity participantEntity) {
        final BrandEntity brandEntity = BrandEntity.builder()
                .data(dataEntity)
                .name(brand.getName())
                .participant(participantEntity)
                .build();
        final List<CompanyEntity> companyEntities = convertListOfCompaniesToListOfCompaniesEntity(brand.getCompanies(), brandEntity);

        brandEntity.setCompanies(companyEntities);
        return brandEntity;
    }

    private static List<CompanyEntity> convertListOfCompaniesToListOfCompaniesEntity(final List<Company> companies, final BrandEntity brandEntity) {
        return companies.stream().map(company -> CompanyToCompanyEntityMapper.convert(company, brandEntity)).collect(Collectors.toList());
    }
}
