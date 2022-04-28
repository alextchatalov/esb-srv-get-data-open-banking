package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceBusinessAccounts;
import com.getdata.dataprovider.entity.Interval;

import java.util.Arrays;

public class ServiceBusinessAccountsFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(ServiceBusinessAccounts.class).addTemplate(VALID, new Rule() {{
            add(ServiceBusinessAccounts.Fields.name, "test");
            add(ServiceBusinessAccounts.Fields.code, "123");
            add(ServiceBusinessAccounts.Fields.chargingTriggerInfo, "test");
            add(ServiceBusinessAccounts.Fields.prices, Arrays.asList(createPrice()));
            add(ServiceBusinessAccounts.Fields.minimum, createMinimum());
            add(ServiceBusinessAccounts.Fields.maximum, createMaximum());
            add(ServiceBusinessAccounts.Fields.eventLimitQuantity, "1");
            add(ServiceBusinessAccounts.Fields.freeEventQuantity, "1");

        }});
    }


    private Maximum createMaximum() {

        return Maximum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Minimum createMinimum() {

        return Minimum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Price createPrice() {
        return Price.builder()
                .interval(Interval.FAIXA_1.getOriginal())
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private Customers createCustomers() {

        return Customers.builder()
                .rate("123")
                .build();
    }
}