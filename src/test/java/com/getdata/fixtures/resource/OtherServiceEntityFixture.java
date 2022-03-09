package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.OtherServiceEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Arrays;

public class OtherServiceEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(OtherServiceEntity.class).addTemplate(VALID, new Rule() {{
            add(OtherServiceEntity.Fields.name, "test");
            add(OtherServiceEntity.Fields.code, "test");
            add(OtherServiceEntity.Fields.chargingTriggerInfo, "test");
            add(OtherServiceEntity.Fields.prices, Arrays.asList(createPrice()));
            add(OtherServiceEntity.Fields.minimum, createMinimum());
            add(OtherServiceEntity.Fields.maximum, createMaximum());

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
                .interval("123")
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
