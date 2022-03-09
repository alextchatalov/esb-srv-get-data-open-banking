package com.getdata.fixtures.resource;

import com.getdata.core.model.MinimumBalance;
import com.getdata.core.model.TermsConditions;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TermsConditionsFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(TermsConditions.class).addTemplate(VALID, new Rule() {{
            add(TermsConditions.Fields.minimumBalance, createMinimumBalance());
            add(TermsConditions.Fields.elegibilityCriteriaInfo, "test");
            add(TermsConditions.Fields.closingProcessInfo, "1");

        }});
    }

    private MinimumBalance createMinimumBalance() {
        return MinimumBalance.builder()
                .currency("123")
                .value("123")
                .build();
    }

}
