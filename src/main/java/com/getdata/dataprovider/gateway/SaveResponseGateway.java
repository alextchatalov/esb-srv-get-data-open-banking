package com.getdata.dataprovider.gateway;

import com.getdata.core.model.Data;
import com.getdata.core.usecase.SaveResponseBoundary;
import com.getdata.dataprovider.entity.DataEntity;
import com.getdata.dataprovider.mapper.DataToDataEntityMapper;
import com.getdata.dataprovider.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveResponseGateway implements SaveResponseBoundary {


    private final DataRepository repository;
    private final DataToDataEntityMapper dataToDataEntityMapper;

    @Override
    public void execute(final Data data) {
        final DataEntity dataEntity = dataToDataEntityMapper.convert(data);
        repository.save(dataEntity);
    }
}
