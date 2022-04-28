package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.OtherService;
import com.getdata.core.model.Price;
import com.getdata.dataprovider.entity.Interval;

import java.util.Arrays;

public class OtherServiceFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(OtherService.class).addTemplate(VALID, new Rule() {{
            add(OtherService.Fields.name, "test");
            add(OtherService.Fields.code, "test");
            add(OtherService.Fields.chargingTriggerInfo, "test");
            add(OtherService.Fields.prices, Arrays.asList(createPrice()));
            add(OtherService.Fields.minimum, createMinimum());
            add(OtherService.Fields.maximum, createMaximum());

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
