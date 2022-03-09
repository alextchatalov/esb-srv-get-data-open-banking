package com.getdata.fixtures.resource;

import com.getdata.core.model.MinimumBalance;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MinimumBalanceFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(MinimumBalance.class).addTemplate(VALID, new Rule() {{
            add(MinimumBalance.Fields.value, "123");
            add(MinimumBalance.Fields.currency, "123");

        }});
    }

}
