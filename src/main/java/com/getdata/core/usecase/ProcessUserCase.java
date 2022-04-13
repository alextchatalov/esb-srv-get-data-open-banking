package com.getdata.core.usecase;

import com.getdata.core.model.Participant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ProcessUserCase {

    @Autowired
    ProcessJsonUserCase processJsonUserCase;

    CreateParticipantsBoundary createParticipantsBoundary;

    public List<Participant> execute(final String participantJson) {
        final List<Participant> participants = processJsonUserCase.execute(participantJson);
        final List<Participant> participantsSalved = new ArrayList<>();
        for (final Participant participant : participants) {
                
            log.info("Saving new Participant: {}", participant.getCustomerFriendlyName());
                final Participant salved = createParticipantsBoundary.save(participant);
                participantsSalved.add(salved);
        }
        return participantsSalved;
    }
}
