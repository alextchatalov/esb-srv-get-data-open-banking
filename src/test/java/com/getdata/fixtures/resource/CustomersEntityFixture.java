package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.CustomersEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CustomersEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(CustomersEntity.class).addTemplate(VALID, new Rule() {{
            add(CustomersEntity.Fields.rate, "123");

        }});
    }

}
