package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;

public class RequiredWarrantiesEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {

        Fixture.of(RequiredWarrantiesEntity.class).addTemplate(VALID, new Rule() {{
            add(RequiredWarrantiesEntity.Fields.warranties, "test");

        }});
    }

}
