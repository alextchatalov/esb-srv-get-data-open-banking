package com.getdata.dataprovider.mapper;

import com.getdata.core.model.Application;
import com.getdata.core.model.InterestRate;
import com.getdata.dataprovider.entity.ApplicationEntity;
import com.getdata.dataprovider.entity.InterestRateEntity;
import com.getdata.dataprovider.entity.PersonalLoanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Lazy
public class InterestRateToInterestRateEntityMapper {

    @NonNull
    public static InterestRateEntity convert(final InterestRate interestRate, final PersonalLoanEntity personalLoanEntity) {

        final InterestRateEntity interestRateEntity = InterestRateEntity.builder()
                .personalLoan(personalLoanEntity)
                .referentialRateIndexer(interestRate.getReferentialRateIndexer())
                .rate(interestRate.getRate())
                .minimumRate(interestRate.getMinimumRate())
                .maximumRate(interestRate.getMaximumRate())
                .build();

        interestRateEntity.setApplications(convertApplicationListToApplicationEntityList(interestRate.getApplications(), interestRateEntity));

        return interestRateEntity;
    }

    private static List<ApplicationEntity> convertApplicationListToApplicationEntityList(final List<Application> applications, final InterestRateEntity interestRateEntity) {
        if (applications != null && !applications.isEmpty()) {
            return applications.stream().map(application -> ApplicationToApplicationEntityMapper.convert(application, interestRateEntity)).collect(Collectors.toList());
        }
        return null;
    }

}
