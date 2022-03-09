package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.IncomeRateEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class IncomeRateEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(IncomeRateEntity.class).addTemplate(VALID, new Rule() {{
            add(IncomeRateEntity.Fields.savingAccount, "123");
            add(IncomeRateEntity.Fields.prepaidPaymentAccount, "123");

        }});
    }

}
