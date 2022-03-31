package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.IndexerEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;

import java.util.Arrays;

public class InterestRateEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(InterestRateEntity.class).addTemplate(VALID, new Rule() {{
            add(InterestRateEntity.Fields.referentialRateIndexer, "1");
            add(InterestRateEntity.Fields.rate, "1");
            add(InterestRateEntity.Fields.applications, Arrays.asList(createApplication()));
            add(InterestRateEntity.Fields.minimumRate, "1");
            add(InterestRateEntity.Fields.maximumRate, "1");

        }});
    }

    private ApplicationEntity createApplication() {
        return ApplicationEntity.builder()
                .interval("1")
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
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
