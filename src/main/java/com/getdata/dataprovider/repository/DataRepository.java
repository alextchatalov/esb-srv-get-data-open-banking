package com.getdata.dataprovider.repository;

import com.getdata.dataprovider.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataEntity, String> {


}
