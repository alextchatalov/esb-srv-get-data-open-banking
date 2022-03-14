package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.FeesBusinessAccountsEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import com.getdata.core.model.FeesBusinessAccounts;
import com.getdata.core.model.ServiceBusinessAccounts;
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
public class FeesBusinessAccountsToFeesBusinessAccountsEntityMapper implements Converter<FeesBusinessAccounts, FeesBusinessAccountsEntity> {

    private final ServiceBusinessAccountsToServiceBusinessAccountsEntityMapper serviceBusinessAccountsToServiceBusinessAccountsEntityMapper;

    @Override
    @NonNull
    public FeesBusinessAccountsEntity convert(final FeesBusinessAccounts feesBusinessAccounts) {
        return FeesBusinessAccountsEntity.builder()
                .services(convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(feesBusinessAccounts.getServices()))
                .build();
    }

    private List<ServiceBusinessAccountsEntity> convertListOfServiceBusinessAccountsToListOfServiceBusinessAccountsEntity(final List<ServiceBusinessAccounts> serviceBusinessAccounts) {
        return serviceBusinessAccounts.stream().map(serviceBusinessAccountsToServiceBusinessAccountsEntityMapper::convert).collect(Collectors.toList());
    }

}
