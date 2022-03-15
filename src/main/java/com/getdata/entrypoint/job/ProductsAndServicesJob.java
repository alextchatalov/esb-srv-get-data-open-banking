package com.getdata.entrypoint.job;

import com.getdata.core.model.Category;
import com.getdata.core.model.Request;
import com.getdata.core.model.Response;
import com.getdata.core.model.Root;
import com.getdata.core.usecase.FindUrlsProductsAndServicesUserCase;
import com.getdata.core.usecase.RequestProductsAndServicesUserCase;
import com.getdata.core.usecase.SaveResponseUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@EnableScheduling
@AllArgsConstructor
public class ProductsAndServicesJob {


    private final FindUrlsProductsAndServicesUserCase findUrlsProductsAndServicesUserCase;
    private final RequestProductsAndServicesUserCase requestProductsAndServicesUserCase;
    private final SaveResponseUseCase saveResponseUseCase;

    private final List<String> banksFilters = Arrays.asList("Itaú", "Itaucard", "Itaú BBA");

    @Scheduled(fixedDelay = 50000000)
    void run() {

        final List<Request> apis = findUrlsProductsAndServicesUserCase.execute();
        final List<Response> response = requestProductsAndServicesUserCase.execute(apis);
        final List<Root> rootDataObjectFromJsonResponse = response.stream().filter(r -> filterByBankAndCategory(r)).map(Response::getObjectFromJsonResponse).collect(Collectors.toList());
//        for (final Response r : response) {
//            if (filterByBankAndCategory(r)) {
//                try {
//                    final Root requestMapped = r.getObjectFromJsonResponse();
//                } catch (final MapperException m) {
//                    log.debug(r.getResponse());
//                }
//            }
//
//        }
        System.out.println("Saving: " + rootDataObjectFromJsonResponse.size());
        saveResponseUseCase.execute(rootDataObjectFromJsonResponse);
        log.info("Job finished!");
    }

    private boolean filterByBankAndCategory(final Response r) {
        final boolean filterCategory = Category.PERSONAL_ACCOUNTS.equals(r.getCategory()) || Category.BUSINESS_ACCOUNTS.equals(r.getCategory());
        final boolean filterBank = banksFilters.contains(r.getParticipant().getCustomerFriendlyName());
        System.out.println("Banking Name: " + r.getParticipant().getCustomerFriendlyName() + " filterBank: " + filterBank + " filterCategory " + filterCategory + "");
        return filterBank && filterCategory;
    }
}
