package com.getdata.entrypoint.job;

import com.getdata.core.usecase.CreateParticipantsUserCase;
import com.getdata.core.usecase.RequestParticipantsUserCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@EnableScheduling
@AllArgsConstructor
public class ParticipantsJob {


    private final RequestParticipantsUserCase requestParticipantsUserCase;
    private final CreateParticipantsUserCase createParticipantsUserCase;

    //@Scheduled(fixedDelay = 500000)
    private void run() {

        try {
            final String participantsJson = requestParticipantsUserCase.execute();
            createParticipantsUserCase.execute(participantsJson);
        } catch (final IOException e) {
            log.error("Can not save participants: {}", e.getMessage());
        }
        log.info("Process ParticipantsJob finish");
    }
}
