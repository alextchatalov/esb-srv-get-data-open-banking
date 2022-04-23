package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Application;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.IndexerEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.Interval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

@Slf4j
@Lazy
public class ApplicationToApplicationEntityMapper {

    @NonNull
    public static ApplicationEntity convert(final Application application, final InterestRateEntity interestRateEntity) {

        final ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .interestRate(interestRateEntity)
                .interval(Interval.getByOriginal(application.getInterval()))
                .build();

        final IndexerEntity indexerEntity = createIndexer(application, applicationEntity);
        applicationEntity.setCustomers(CustomersToCustomersEntityMapper.convertWithApplication(application.getCustomers(), applicationEntity));
        applicationEntity.setIndexer(indexerEntity);

        return applicationEntity;
    }

    private static IndexerEntity createIndexer(final Application application, final ApplicationEntity applicationEntity) {
        return IndexerEntity.builder()
                .application(applicationEntity)
                .rate(application.getIndexer().getRate())
                .build();
    }

}
