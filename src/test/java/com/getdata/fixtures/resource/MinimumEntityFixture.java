package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.MinimumEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MinimumEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(MinimumEntity.class).addTemplate(VALID, new Rule() {{
            add(MinimumEntity.Fields.value, "123");
            add(MinimumEntity.Fields.currency, "123");

        }});
    }

}
