package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionMethodsToTransactionMethodsEntity {

    public static List<TransactionMethodsEntity> convert(final List<String> methods, final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccount) {

        List<TransactionMethodsEntity> transactionMethodsEntities = new ArrayList<>();

        methods.stream().forEach(method -> {
            TransactionMethodsEntity openingClosingChannelsEntity = TransactionMethodsEntity.builder()
                    .method(method)
                    .personalAccount(personalAccount)
                    .businessAccount(businessAccount)
                    .build();
            transactionMethodsEntities.add(openingClosingChannelsEntity);
        });

        return transactionMethodsEntities;
    }
}
