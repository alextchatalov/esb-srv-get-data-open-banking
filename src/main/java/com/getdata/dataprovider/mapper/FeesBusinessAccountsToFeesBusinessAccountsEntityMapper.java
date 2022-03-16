package com.getdata.dataprovider.mapper;

import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.ServiceBusinessAccounts;
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
    public static FeesBusinessAccountsEntity convert(final FeesBusinessAccounts feesBusinessAccounts) {
        return FeesBusinessAccountsEntity.builder()
                .services(convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(feesBusinessAccounts.getServices()))
                .build();
    }

    private static List<ServiceBusinessAccountsEntity> convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(final List<ServiceBusinessAccounts> serviceBusinessAccounts) {
        return serviceBusinessAccounts.stream().map(ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper::convert).collect(Collectors.toList());
    }

}
