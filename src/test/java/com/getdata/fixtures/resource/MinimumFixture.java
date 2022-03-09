package com.getdata.fixtures.resource;

import com.getdata.core.model.Minimum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MinimumFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Minimum.class).addTemplate(VALID, new Rule() {{
            add(Minimum.Fields.value, "123");
            add(Minimum.Fields.currency, "123");

        }});
    }

}
