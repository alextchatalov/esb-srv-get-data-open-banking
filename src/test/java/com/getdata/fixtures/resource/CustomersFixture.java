package com.getdata.fixtures.resource;

import com.getdata.core.model.Customers;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CustomersFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Customers.class).addTemplate(VALID, new Rule() {{
            add(Customers.Fields.rate, "123");

        }});
    }

}
