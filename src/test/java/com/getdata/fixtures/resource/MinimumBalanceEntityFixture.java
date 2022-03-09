package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MinimumBalanceEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(MinimumBalanceEntity.class).addTemplate(VALID, new Rule() {{
            add(MinimumBalanceEntity.Fields.value, "123");
            add(MinimumBalanceEntity.Fields.currency, "123");

        }});
    }

}
