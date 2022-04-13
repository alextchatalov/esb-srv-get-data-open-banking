package com.getdata.dataprovider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://data.directory.openbankingbrasil.org.br", name = "request-participants")
public interface ParticipantClient {

    @GetMapping("/participants")
    String getParticipantsFromRepository();

}
