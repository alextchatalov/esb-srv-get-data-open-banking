package com.getdata.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import com.getdata.core.model.Data;
import com.getdata.core.model.Root;
import com.getdata.fixtures.FixtureLoader;
import com.getdata.fixtures.resource.DataFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SaveResponseUseCaseTest {

    @InjectMocks
    SaveResponseUseCase saveResponseUseCase;

    @Mock
    SaveResponseBoundary saveResponseBoundary;

    @BeforeAll
    public static void setup() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    void given_valid_response_When_salve_response_Then_save_success() {

        final Data dataMock = Fixture.from(Data.class).gimme(DataFixture.VALID);
        final Root rootMock = Root.builder()
                .data(dataMock)
                .build();

        doNothing().when(saveResponseBoundary).execute(any(Data.class));

        saveResponseUseCase.execute(Arrays.asList(rootMock));
        verify(saveResponseBoundary, times(1)).execute(any(Data.class));
    }
}
