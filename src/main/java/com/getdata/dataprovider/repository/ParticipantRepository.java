package com.getdata.dataprovider.repository;

import com.getdata.core.model.ParticipantStatus;
import com.getdata.dataprovider.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, String> {

    List<ParticipantEntity> findByStatus(ParticipantStatus status);
}
