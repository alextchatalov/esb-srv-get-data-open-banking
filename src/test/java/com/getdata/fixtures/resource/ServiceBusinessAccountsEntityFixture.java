package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.ServiceBusinessAccountsEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Arrays;

public class ServiceBusinessAccountsEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(ServiceBusinessAccountsEntity.class).addTemplate(VALID, new Rule() {{
            add(ServiceBusinessAccountsEntity.Fields.name, "test");
            add(ServiceBusinessAccountsEntity.Fields.code, "123");
            add(ServiceBusinessAccountsEntity.Fields.chargingTriggerInfo, "test");
            add(ServiceBusinessAccountsEntity.Fields.prices, Arrays.asList(createPrice()));
            add(ServiceBusinessAccountsEntity.Fields.minimum, createMinimum());
            add(ServiceBusinessAccountsEntity.Fields.maximum, createMaximum());
            add(ServiceBusinessAccountsEntity.Fields.eventLimitQuantity, "1");
            add(ServiceBusinessAccountsEntity.Fields.freeEventQuantity, "1");

        }});
    }


    private MaximumEntity createMaximum() {

        return MaximumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private MinimumEntity createMinimum() {

        return MinimumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private PriceEntity createPrice() {
        return PriceEntity.builder()
                .interval(Interval.FAIXA_1)
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private CustomersEntity createCustomers() {

        return CustomersEntity.builder()
                .rate("123")
                .build();
    }
}