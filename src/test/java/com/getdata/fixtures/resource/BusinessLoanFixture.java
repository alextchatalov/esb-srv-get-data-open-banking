package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.core.model.Application;
import com.getdata.core.model.BusinessLoan;
import com.getdata.core.model.Customers;
import com.getdata.core.model.FeesLoan;
import com.getdata.core.model.Indexer;
import com.getdata.core.model.InterestRate;
import com.getdata.core.model.Maximum;
import com.getdata.core.model.Minimum;
import com.getdata.core.model.Price;
import com.getdata.core.model.ServiceLoans;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.ReferentialRateIndexer;
import com.getdata.dataprovider.entity.RequiredWarranties;

import java.util.Arrays;

public class BusinessLoanFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(BusinessLoan.class).addTemplate(VALID, new Rule() {{
            add(BusinessLoan.Fields.type, "EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS");
            add(BusinessLoan.Fields.fees, createFeesLoan());
            add(BusinessLoan.Fields.interestRates, Arrays.asList(createInterestRate()));
            add(BusinessLoan.Fields.requiredWarranties, Arrays.asList(RequiredWarranties.ACORDOS_COMPENSACAO.name()));
            add(BusinessLoan.Fields.termsConditions, "test");

        }});
    }

    private InterestRate createInterestRate() {
        return InterestRate.builder()
                .referentialRateIndexer(ReferentialRateIndexer.CREDITO_RURAL_TCR_POS.name())
                .rate("1")
                .applications(Arrays.asList(createApplication()))
                .minimumRate("1")
                .maximumRate("1")
                .build();
    }

    private Application createApplication() {
        return Application.builder()
                .interval(Interval.FAIXA_1.getOriginal())
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
    }

    private Indexer createIndexer() {
        return Indexer.builder()
                .rate("1")
                .build();
    }

    private FeesLoan createFeesLoan() {
        return FeesLoan.builder()
                .services(Arrays.asList(createServicePersonalLoans()))
                .build();
    }

    private ServiceLoans createServicePersonalLoans() {
        return ServiceLoans.builder()
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
                .chargingTriggerInfo("test")
                .prices(Arrays.asList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private Maximum createMaximum() {

        return Maximum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Minimum createMinimum() {

        return Minimum.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private Price createPrice() {
        return Price.builder()
                .interval(Interval.FAIXA_1.getOriginal())
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private Customers createCustomers() {

        return Customers.builder()
                .rate("123")
                .build();
    }

}
