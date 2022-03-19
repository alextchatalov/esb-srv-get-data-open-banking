package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OpeningClosingChannelsToOpeningClosingChannelsEntity {

    @NotNull
    public static List<OpeningClosingChannelsEntity> convertWithPersonalAccounts(final List<String> channels, final PersonalAccountEntity personalAccountEntity) {
        return convert(channels, personalAccountEntity, null);
    }

    @NotNull
    public static List<OpeningClosingChannelsEntity> convertWithBusinessAccounts(final List<String> channels, final BusinessAccountEntity businessAccountEntity) {
        return convert(channels, null, businessAccountEntity);
    }

    private static List<OpeningClosingChannelsEntity> convert(final List<String> channels, final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccount) {

        final List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = new ArrayList<>();

        channels.stream().forEach(channel -> {
            final OpeningClosingChannelsEntity openingClosingChannelsEntity = OpeningClosingChannelsEntity.builder()
                    .channel(channel)
                    .personalAccount(personalAccount)
                    .businessAccount(businessAccount)
                    .build();
            openingClosingChannelsEntities.add(openingClosingChannelsEntity);
        });

        return openingClosingChannelsEntities;
    }
}
