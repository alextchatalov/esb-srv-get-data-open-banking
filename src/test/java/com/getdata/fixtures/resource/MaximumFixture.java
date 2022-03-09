package com.getdata.fixtures.resource;

import com.getdata.core.model.Maximum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MaximumFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(Maximum.class).addTemplate(VALID, new Rule() {{
            add(Maximum.Fields.value, "123");
            add(Maximum.Fields.currency, "123");

        }});
    }

}
