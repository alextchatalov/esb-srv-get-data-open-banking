package com.getdata.fixtures.resource;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.BusinessLoanEntity;
import com.getdata.dataprovider.entity.CustomersEntity;
import com.getdata.dataprovider.entity.FeesLoanEntity;
import com.getdata.dataprovider.entity.IndexerEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.Interval;
import com.getdata.dataprovider.entity.MaximumEntity;
import com.getdata.dataprovider.entity.MinimumEntity;
import com.getdata.dataprovider.entity.PriceEntity;
import com.getdata.dataprovider.entity.PriorityServiceEntity;
import com.getdata.dataprovider.entity.ReferentialRateIndexer;
import com.getdata.dataprovider.entity.RequiredWarranties;
import com.getdata.dataprovider.entity.RequiredWarrantiesEntity;
import com.getdata.dataprovider.entity.TypeLoan;

import java.util.Arrays;

public class BusinessLoanEntityFixture implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        loadData();
    }

    private void loadData() {
        Fixture.of(BusinessLoanEntity.class).addTemplate(VALID, new Rule() {{
            add(BusinessLoanEntity.Fields.type, TypeLoan.EMPRESTIMO_CAPITAL_GIRO_PRAZO_VENCIMENTO_ATE_365_DIAS);
            add(BusinessLoanEntity.Fields.fees, createFeesLoan());
            add(BusinessLoanEntity.Fields.interestRates, Arrays.asList(createInterestRate()));
            add(BusinessLoanEntity.Fields.requiredWarranties, Arrays.asList(createRequiredWarranties()));
            add(BusinessLoanEntity.Fields.termsConditions, "test");

        }});
    }

    private RequiredWarrantiesEntity createRequiredWarranties() {
        return RequiredWarrantiesEntity.builder()
                .warranties(RequiredWarranties.ACORDOS_COMPENSACAO)
                .build();
    }

    private InterestRateEntity createInterestRate() {
        return InterestRateEntity.builder()
                .referentialRateIndexer(ReferentialRateIndexer.CREDITO_RURAL_TCR_POS)
                .rate("1")
                .applications(Arrays.asList(createApplication()))
                .minimumRate("1")
                .maximumRate("1")
                .build();
    }

    private ApplicationEntity createApplication() {
        return ApplicationEntity.builder()
                .interval(Interval.FAIXA_1)
                .indexer(createIndexer())
                .customers(createCustomers())
                .build();
    }

    private IndexerEntity createIndexer() {
        return IndexerEntity.builder()
                .rate("1")
                .build();
    }

    private FeesLoanEntity createFeesLoan() {
        return FeesLoanEntity.builder()
                .services(Arrays.asList(createServicePersonalLoans()))
                .build();
    }

    private PriorityServiceEntity createServicePersonalLoans() {
        return PriorityServiceEntity.builder()
                .name("ANUIDADE_CARTAO_BASICO_NACIONAL")
                .code("EXTRATO_MES_E")
                .chargingTriggerInfo("test")
                .prices(Arrays.asList(createPrice()))
                .minimum(createMinimum())
                .maximum(createMaximum())
                .build();
    }

    private MaximumEntity createMaximum() {

        return MaximumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private MinimumEntity createMinimum() {

        return MinimumEntity.builder()
                .currency("123")
                .value("123")
                .build();
    }

    private PriceEntity createPrice() {
        return PriceEntity.builder()
                .interval(Interval.FAIXA_1)
                .value("123")
                .currency("123")
                .customers(createCustomers())
                .monthlyFee("123")
                .build();
    }

    private CustomersEntity createCustomers() {

        return CustomersEntity.builder()
                .rate("123")
                .build();
    }

}
