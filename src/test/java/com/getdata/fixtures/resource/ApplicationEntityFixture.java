package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.IndexerEntity;

public class ApplicationEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {

        Fixture.of(ApplicationEntity.class).addTemplate(VALID, new Rule() {{
            add(ApplicationEntity.Fields.interval, "1");
            add(ApplicationEntity.Fields.indexer, createIndexer());
            add(ApplicationEntity.Fields.customers, createCustomers());

        }});
    }

    private CustomersEntity createCustomers() {
        return CustomersEntity.builder()
                .rate("123")
                .build();
    }

    private IndexerEntity createIndexer() {
        return IndexerEntity.builder()
                .rate("1")
                .build();
    }

}
