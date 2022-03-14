package com.getdata.core.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RequestParticipantsUserCase {

    public String execute() throws IOException {
        log.info("Consulting participants on diretory open banking...");
        final URL url = new URL("https://data.directory.openbankingbrasil.org.br/participants");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        final String participantsJson = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        log.info("Consult finish");
        return participantsJson;
    }
}
