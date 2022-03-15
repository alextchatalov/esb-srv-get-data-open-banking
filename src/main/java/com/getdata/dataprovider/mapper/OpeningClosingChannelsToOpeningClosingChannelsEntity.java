package com.getdata.dataprovider.mapper;

import com.getdata.dataprovider.entity.BusinessAccountEntity;
import com.getdata.dataprovider.entity.OpeningClosingChannelsEntity;
import com.getdata.dataprovider.entity.PersonalAccountEntity;

import java.util.ArrayList;
import java.util.List;

public class OpeningClosingChannelsToOpeningClosingChannelsEntity {

    public static List<OpeningClosingChannelsEntity> convert(final List<String> channels, final PersonalAccountEntity personalAccount, final BusinessAccountEntity businessAccount) {

        List<OpeningClosingChannelsEntity> openingClosingChannelsEntities = new ArrayList<>();

        channels.stream().forEach(channel -> {
            OpeningClosingChannelsEntity openingClosingChannelsEntity = OpeningClosingChannelsEntity.builder()
                    .channel(channel)
                    .personalAccount(personalAccount)
                    .businessAccount(businessAccount)
                    .build();
            openingClosingChannelsEntities.add(openingClosingChannelsEntity);
        });

        return openingClosingChannelsEntities;
    }
}
