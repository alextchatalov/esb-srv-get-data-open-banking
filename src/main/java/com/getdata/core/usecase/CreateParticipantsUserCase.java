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
public class CreateParticipantsUserCase {

    @Autowired
    ProcessJsonUserCase processJsonUserCase;

    CreateParticipantsBoundary createParticipantsBoundary;
    //FindParticipantByOrganisationIdUserCase findParticipantByOrganisationIdUserCase;

    public List<Participant> execute(final String participantJson) {
        final List<Participant> participants = processJsonUserCase.execute(participantJson);
        final List<Participant> participantsSalved = new ArrayList<>();
        for (final Participant participant : participants) {
            final Participant participantFound = null;
            //findParticipantByOrganisationIdUserCase.findByOrganisationId(participant.getOrganisationId());
            if (participantFound != null) {
                log.info("Updating APIS from Participant: {}", participantFound.getCustomerFriendlyName());
                participantFound.getApiResources().clear();
                participantFound.getApiResources().addAll(participant.getApiResources());
                final Participant participantSaved = createParticipantsBoundary.save(participantFound);
                participantsSalved.add(participantSaved);
            } else {
                log.info("Saving new Participant: {}", participant.getCustomerFriendlyName());
                final Participant salved = createParticipantsBoundary.save(participant);
                participantsSalved.add(salved);
            }


        }
        return participantsSalved;
    }
}
