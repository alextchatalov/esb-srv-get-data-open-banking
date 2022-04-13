package com.getdata.dataprovider.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "request-products-and-services", url = "https://this-is-a-placeholder.com")
public interface RequestProductsAndServicesClient {

    @GetMapping
    Response request(URI url);

}
