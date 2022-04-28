package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Application;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Indexer;
import com.getdata.core.model.InterestRate;
import com.getdata.dataprovider.entity.Interval;

import java.util.Arrays;

public class InterestRateFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(InterestRate.class).addTemplate(VALID, new Rule() {{
            add(InterestRate.Fields.referentialRateIndexer, "SEM_INDEXADOR_TAXA");
            add(InterestRate.Fields.rate, "1");
            add(InterestRate.Fields.applications, Arrays.asList(createApplication()));
            add(InterestRate.Fields.minimumRate, "1");
            add(InterestRate.Fields.maximumRate, "1");

        }});
    }

    private Application createApplication() {
        return Application.builder()
                .interval(Interval.FAIXA_1.getOriginal())
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
    }

    private Customers createCustomers() {

        return Customers.builder()
                .rate("123")
                .build();
    }

    private Indexer createIndexer() {
        return Indexer.builder()
                .rate("1")
                .build();
    }

}
