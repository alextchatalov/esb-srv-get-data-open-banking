package com.getdata.fixtures.resource;

import com.getdata.core.model.ServiceFromServiceBundle;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ServiceFromServiceBundleFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(ServiceFromServiceBundle.class).addTemplate(VALID, new Rule() {{
            add(ServiceFromServiceBundle.Fields.code, "123");
            add(ServiceFromServiceBundle.Fields.chargingTriggerInfo, "test");
            add(ServiceFromServiceBundle.Fields.eventLimitQuantity, "1");
            add(ServiceFromServiceBundle.Fields.freeEventQuantity, "1");

        }});
    }

}
