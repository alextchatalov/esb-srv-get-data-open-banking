package com.getdata.fixtures.resource;

import com.getdata.core.model.Customers;
import com.getdata.core.model.Price;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PriceFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Price.class).addTemplate(VALID, new Rule() {{
            add(Price.Fields.interval, "123");
            add(Price.Fields.value, "123");
            add(Price.Fields.currency, "123");
            add(Price.Fields.customers, createCustomers());
            add(Price.Fields.monthlyFee, "123");

        }});
    }

    private Customers createCustomers() {

        return Customers.builder()
                .rate("123")
                .build();
    }

}
