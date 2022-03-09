package com.getdata.fixtures.resource;

import com.getdata.dataprovider.entity.MinimumBalanceEntity;
import com.getdata.dataprovider.entity.TermsConditionsEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TermsConditionsEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(TermsConditionsEntity.class).addTemplate(VALID, new Rule() {{
            add(TermsConditionsEntity.Fields.minimumBalance, createMinimumBalance());
            add(TermsConditionsEntity.Fields.elegibilityCriteriaInfo, "test");
            add(TermsConditionsEntity.Fields.closingProcessInfo, "1");

        }});
    }

    private MinimumBalanceEntity createMinimumBalance() {
        return MinimumBalanceEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

}
