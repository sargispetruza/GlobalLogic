package com.task.trainee.controllers;

import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorListRepository;
import com.task.trainee.repo.SensorRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileWriter;
import java.io.IOException;

//TODO: It is not a good idea to put verbs in class names. Verbs are more applicable in method names.
@Controller
public class GenerateJsonController {
    // изменить на ваш полный путь для корректной записи
    //TODO: please, use relative path instead of absolute
    private static final String URL_FOR_REPORT = "D:\\IdeaProjects\\GlobalLogic\\trainee\\src\\main\\resources\\reports\\report.json";
    private final SensorRepository sensorRepository;
    private final SensorListRepository sensorListRepository;

    public GenerateJsonController(SensorRepository sensorRepository, SensorListRepository sensorListRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorListRepository = sensorListRepository;
    }

    @GetMapping("/generate")
    public String generate() {
        return "redirect:/json";
    }

    @PostMapping("/generate")
    public String generatePost() {
        createJson();
        return "redirect:/json";
    }

    //TODO: all business logic should be on the service layer
    private void createJson() {
        long allScaners = 0;
        long brokenScaners = 0;
        long onlineScaners = 0;
        Iterable<Sensor> sensorList = sensorRepository.findAll();
        int counter = 0;
        for (Sensor sensor : sensorList) {
            counter++;
            if (sensor.getId() != null) {
                allScaners++;
            }
            if (sensor.getSensor_status().getStatus().equals("Online")) {
                onlineScaners++;
            }
        }
        brokenScaners = sensorListRepository.getUrlList().size() - counter;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("all_scaners", allScaners);
        jsonObject.put("broken_scaners", brokenScaners);
        jsonObject.put("online_scaners", onlineScaners);

        // указать полный путь к файлу в переменной URL_FOR_REPORT
        try (FileWriter fileWriter = new FileWriter(URL_FOR_REPORT)) {
            fileWriter.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // печатает report в консоль (для удобства)
        //TODO: Try to use logger instead of System.out
        System.out.println(jsonObject.toJSONString());
    }
}
