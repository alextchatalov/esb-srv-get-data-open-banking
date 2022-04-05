package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.FeesLoanEntity;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;

import java.util.Arrays;

public class FeesLoanEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(FeesLoanEntity.class).addTemplate(VALID, new Rule() {{
            add(FeesLoanEntity.Fields.services, Arrays.asList(createServicePersonalLoans()));

        }});
    }

    private PriorityServiceEntity createServicePersonalLoans() {
        return PriorityServiceEntity.builder()
                .name("test")
                .code("test")
                .chargingTriggerInfo("test")
                .prices(Arrays.asList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
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

}
