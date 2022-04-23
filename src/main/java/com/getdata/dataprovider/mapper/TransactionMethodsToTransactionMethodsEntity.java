package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.getdata.dataprovider.entity.TransactionMethod;
import com.getdata.dataprovider.entity.TransactionMethodsEntity;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TransactionMethodsToTransactionMethodsEntity {

    @NonNull
    public static List<TransactionMethodsEntity> convertWithPersonalAccounts(final List<String> methods, final PersonalAccountEntity personalAccount) {

        return convert(methods, personalAccount, null);
    }

    @NonNull
    public static List<TransactionMethodsEntity> convertWithBusinessAccounts(final List<String> methods, final BusinessAccountEntity businessAccount) {

        return convert(methods, null, businessAccount);
    }

    private static List<TransactionMethodsEntity> convert(final List<String> methods, final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccount) {

        if (methods == null || methods.isEmpty()) {
            return null;
        }

        final List<TransactionMethodsEntity> transactionMethodsEntities = new ArrayList<>();

        methods.stream().forEach(method -> {
            final TransactionMethodsEntity openingClosingChannelsEntity = TransactionMethodsEntity.builder()
                    .method(TransactionMethod.valueOf(method))
                    .personalAccount(personalAccount)
                    .businessAccount(businessAccount)
                    .build();
            transactionMethodsEntities.add(openingClosingChannelsEntity);
        });

        return transactionMethodsEntities;
    }
}
