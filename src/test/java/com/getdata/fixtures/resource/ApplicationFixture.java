package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Application;
import com.getdata.core.model.Customers;
import com.getdata.core.model.Indexer;
import com.getdata.dataprovider.entity.Interval;

public class ApplicationFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Application.class).addTemplate(VALID, new Rule() {{
            add(Application.Fields.interval, Interval.FAIXA_1.getOriginal());
            add(Application.Fields.indexer, createIndexer());
            add(Application.Fields.customers, createCustomers());

        }});
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
