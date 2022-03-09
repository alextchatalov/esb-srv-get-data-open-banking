package com.getdata.fixtures.resource;

import com.getdata.core.model.IncomeRate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class IncomeRateFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(IncomeRate.class).addTemplate(VALID, new Rule() {{
            add(IncomeRate.Fields.savingAccount, "123");
            add(IncomeRate.Fields.prepaidPaymentAccount, "123");

        }});
    }

}
