package com.getdata.core.usecase;

import com.getdata.core.model.Request;
import com.getdata.core.model.Response;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RequestProductsAndServicesUserCase {

    public List<Response> execute(List<Request> apis) {
        log.info("Requesting Products and Services...");
        List<Response> responseProductsAndServices = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        int maxPerList = (apis.size() - 1) / 4;
        List<List<Request>> partition = Lists.partition(apis, maxPerList);
        List<Request> excessList = partition.get(0);
        if (partition.size() > 4) {
            for (int excess = 5; partition.size() >= excess; excess++) {
                excessList.addAll(partition.get(excess - 1));
            }
        }
        Callable<List<Response>> firstTask = createTask(excessList);
        Callable<List<Response>> secondTask = createTask(partition.get(1));
        Callable<List<Response>> thirdTask = createTask(partition.get(2));
        Callable<List<Response>> fourthTask = createTask(partition.get(3));
        List<Future<List<Response>>> listOfFutureTasks = new ArrayList<>();
        listOfFutureTasks.add(executor.submit(firstTask));
        listOfFutureTasks.add(executor.submit(secondTask));
        listOfFutureTasks.add(executor.submit(thirdTask));
        listOfFutureTasks.add(executor.submit(fourthTask));

        List<Response> processedApis = waitAllTaskToFinished(listOfFutureTasks);
        responseProductsAndServices.addAll(processedApis);

        log.info("Process Request Products and Services finish");
        return responseProductsAndServices;
    }

    private List<Response> waitAllTaskToFinished(List<Future<List<Response>>> futures) {
        List<Response> processedApis = new ArrayList<>();

        while (!isAllFutureDone(futures)) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Thread not sleep: " + e);
            }

        }

        for (Future<List<Response>> future : futures) {
            try {
                processedApis.addAll(future.get());
            } catch (Exception e) {
                log.error("Can not process: " + e);
            }
        }

        return processedApis;
    }

    private boolean isAllFutureDone(List<Future<List<Response>>> futures) {
        List<Boolean> futureStates = futures.stream().map(Future::isDone).collect(Collectors.toList());
        return futureStates.stream().allMatch(done -> done == true);
    }

    private Callable<List<Response>> createTask(List<Request> listOfApi) {

        return () -> {
            List<Response> listProcessed = new ArrayList<>();
            int amountProcessed = 0;
            for (Request request : listOfApi) {
                try {
                    calculatePercentage(amountProcessed, listOfApi.size());
                    URL url = new URL(request.getUrl());
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    String productsAndServicesResponse = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));

                    Response response = Response.builder()
                            .participant(request.getParticipant())
                            .lastRequest(request.getLastRequest())
                            .category(request.getCategory())
                            .url(request.getUrl())
                            .version(request.getVersion())
                            .response(productsAndServicesResponse)
                            .build();

                    listProcessed.add(response);
                } catch (IOException e) {
                    log.error("Can not request apis: {}", e.getMessage());
                }
                amountProcessed++;
            }
            return listProcessed;
        };
    }

    public void calculatePercentage(double obtained, double total) {
        final DecimalFormat df2 = new DecimalFormat("#.##");
        double percent = obtained * 100 / total;
        log.info("Processing: " + "Thread: " + Thread.currentThread().getName() + " Percent: " + df2.format(percent) + "%");
    }

    public static <T> List[] splitTheListOfApisInFour(List<T> list) {
        int midIndex = (list.size() - 1) / 4;

        List<List<T>> lists = new ArrayList<>(
                list.stream()
                        .collect(Collectors.partitioningBy(s -> list.indexOf(s) > midIndex))
                        .values()
        );

        // return an array containing both lists
        return new List[]{lists.get(0), lists.get(1)};
    }
}