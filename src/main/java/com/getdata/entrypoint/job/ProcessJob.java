package com.getdata.entrypoint.job;

import com.getdata.core.model.Category;
import com.getdata.core.model.Participant;
import com.getdata.core.model.Request;
import com.getdata.core.model.Response;
import com.getdata.core.model.Root;
import com.getdata.core.usecase.CreateParticipantsUserCase;
import com.getdata.core.usecase.RequestParticipantsBoundary;
import com.getdata.core.usecase.RequestProductsAndServicesUserCase;
import com.getdata.core.usecase.SaveResponseUseCase;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
@Slf4j
@EnableScheduling
@AllArgsConstructor
public class ProcessJob {

    private final RequestParticipantsBoundary requestParticipantsBoundary;
    private final RequestProductsAndServicesUserCase requestProductsAndServicesUserCase;
    private final List<Category> acceptedCategories = Arrays.asList(Category.PERSONAL_ACCOUNTS, Category.BUSINESS_ACCOUNTS, Category.PERSONAL_LOANS, Category.BUSINESS_LOANS);
    private final SaveResponseUseCase saveResponseUseCase;
    private final CreateParticipantsUserCase createParticipantsUserCase;
    final ExecutorService executor = Executors.newFixedThreadPool(4);

    @Scheduled(fixedDelay = 500000)
    private void run() {

        final long start = System.currentTimeMillis();
        log.info("Job started at {}", start / 1000);


        final String participantsJson = requestParticipantsBoundary.execute();

        final List<Participant> participants = createParticipantsUserCase.execute(participantsJson);

        final List<Root> dataProcessed = process(participants);

        saveResponseUseCase.execute(dataProcessed);

        final long end = System.currentTimeMillis();
        log.info("ProductsAndServicesJob ended - " + (end - start) / 1000 + " seconds");
    }

    private List<Root> process(final List<Participant> participants) {

        final int maxPerList = (participants.size() - 1) / 4;
        final List<List<Participant>> partition = Lists.partition(participants, maxPerList);
        final List<Participant> excessList = divideListOfParticipantsInFourList(participants, partition);

        final Callable<List<Root>> firstTask = createTask(excessList);
        final Callable<List<Root>> secondTask = createTask(partition.get(1));
        final Callable<List<Root>> thirdTask = createTask(partition.get(2));
        final Callable<List<Root>> fourthTask = createTask(partition.get(3));

        final List<Future<List<Root>>> listOfFutureTasks = new ArrayList<>();

        listOfFutureTasks.add(executor.submit(firstTask));
        listOfFutureTasks.add(executor.submit(secondTask));
        listOfFutureTasks.add(executor.submit(thirdTask));
        listOfFutureTasks.add(executor.submit(fourthTask));

        return waitAllTaskToFinished(listOfFutureTasks);
    }

    private boolean filterByBankAndCategory(final Response r) {
        final boolean filterCategory = acceptedCategories.contains(r.getCategory());
        return filterCategory;
    }

    private Callable<List<Root>> createTask(final List<Participant> listOfParticipants) {
        final List<Root> data = new ArrayList<>();
        return () -> {
            int amountProcessed = 0;
            for (final Participant participant : listOfParticipants) {
                calculatePercentage(amountProcessed, listOfParticipants.size());

                final List<Request> request = Request.createRequest(participant);
                final List<Response> response = requestProductsAndServicesUserCase.newExecute(request);
                final List<Root> rootDataObjectFromJsonResponse = response.stream().filter(r -> filterByBankAndCategory(r)).map(Response::getObjectFromJsonResponse).collect(Collectors.toList());

                amountProcessed++;
                data.addAll(rootDataObjectFromJsonResponse);
            }
            return data;
        };
    }

    public void calculatePercentage(final double obtained, final double total) {
        final DecimalFormat df2 = new DecimalFormat("#.##");
        final double percent = obtained * 100 / total;
        log.info("Processing: " + "Thread: " + Thread.currentThread().getName() + " Percent: " + df2.format(percent) + "%");
    }

    private List<Root> waitAllTaskToFinished(final List<Future<List<Root>>> futures) {

        final List<Root> data = new ArrayList<>();

        while (!isAllFutureDone(futures)) {

            try {
                Thread.sleep(5000);
            } catch (final InterruptedException e) {
                log.error("Thread not sleep: " + e);
            }

            for (final Future<List<Root>> future : futures) {
                try {
                    data.addAll(future.get());
                } catch (final Exception e) {
                    log.error("Can not process: " + e);
                }
            }

        }
        return data;
    }

    private boolean isAllFutureDone(final List<Future<List<Root>>> futures) {
        final List<Boolean> futureStates = futures.stream().map(Future::isDone).collect(Collectors.toList());
        return futureStates.stream().allMatch(done -> done == true);
    }

    private List<Participant> divideListOfParticipantsInFourList(final List<Participant> participants, final List<List<Participant>> partition) {

        final List<Participant> excessList = partition.get(0);
        if (partition.size() > 4) {
            for (int excess = 5; partition.size() >= excess; excess++) {
                excessList.addAll(partition.get(excess - 1));
            }
        }
        return excessList;
    }


}
