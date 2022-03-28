package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class FeesBusinessAccountsToFeesBusinessAccountsEntityMapper {

    @NonNull
    public static FeesBusinessAccountsEntity convert(final FeesBusinessAccounts feesBusinessAccounts, final BusinessAccountEntity businessAccountEntity) {

        final FeesBusinessAccountsEntity feesBusinessAccountsEntity = FeesBusinessAccountsEntity.builder()
                .businessAccount(businessAccountEntity)
                .build();

        feesBusinessAccountsEntity.setServices(convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(feesBusinessAccounts.getServices(), feesBusinessAccountsEntity));

        return feesBusinessAccountsEntity;
    }

    private static List<ServiceBusinessAccountsEntity> convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(final List<ServiceBusinessAccounts> serviceBusinessAccounts, final FeesBusinessAccountsEntity feesBusinessAccountsEntity) {
        return serviceBusinessAccounts.stream().map(service -> ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper.convert(service, feesBusinessAccountsEntity)).collect(Collectors.toList());
    }

}
