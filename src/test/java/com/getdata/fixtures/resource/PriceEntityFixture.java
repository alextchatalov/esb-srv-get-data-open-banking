package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.PriceEntity;

public class PriceEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(PriceEntity.class).addTemplate(VALID, new Rule() {{
            add(PriceEntity.Fields.interval, Interval.FAIXA_1);
            add(PriceEntity.Fields.value, "123");
            add(PriceEntity.Fields.currency, "123");
            add(PriceEntity.Fields.customers, createCustomers());
            add(PriceEntity.Fields.monthlyFee, "123");

        }});
    }

    private CustomersEntity createCustomers() {

        return CustomersEntity.builder()
                .rate("123")
                .build();
    }

}
