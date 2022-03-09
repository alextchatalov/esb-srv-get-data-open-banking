package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.MaximumEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MaximumEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(MaximumEntity.class).addTemplate(VALID, new Rule() {{
            add(MaximumEntity.Fields.value, "123");
            add(MaximumEntity.Fields.currency, "123");

        }});
    }

}
