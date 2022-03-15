package com.getdata.core.usecase;

import com.getdata.core.model.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class SaveResponseUseCase {

    SaveResponseBoundary saveResponseBoundary;

    public void execute(final List<Root> responses) {
        for (final Root response : responses) {
            saveResponseBoundary.execute(response.getData());
        }
    }
}
