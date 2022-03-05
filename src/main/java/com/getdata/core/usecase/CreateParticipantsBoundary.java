package com.getdata.core.usecase;

import com.getdata.core.model.Participant;

public interface CreateParticipantsBoundary {

    Participant save(Participant participant);
}
