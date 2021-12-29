package com.task.trainee.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorListRepository;
import com.task.trainee.repo.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.net.URL;
import java.util.List;

@Controller
public class MainController {
    private final SensorRepository sensorRepository;
    private final SensorListRepository sensorListRepository;

    public MainController(SensorRepository sensorRepository, SensorListRepository sensorListRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorListRepository = sensorListRepository;
    }

    //TODO: please, output all sensor data on UI
    @GetMapping("json")
    public String json(Model model) {
        model.addAttribute("post", getSensors());
        return "json";
    }

    //TODO: all business logic should be on the service layer
    //TODO: In the task was said that there are some broken sensors, sensors with wrong fields.
    // That is mean that some sensors have not valid fields (wrong email format, incorrect battery percentage, wrong sensor status value).
    // You should handle such cases (add some validation process).
    private Iterable<Sensor> getSensors() {
        List<URL> urlList = sensorListRepository.getUrlList();

        if (urlList != null) {
            for (URL url : urlList) {
                File file = new File(url.getFile());
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

                try {
                    List<Sensor> sensors = objectMapper.readValue(file, new TypeReference<List<Sensor>>() {
                    });
                    Iterable<Sensor> sensorIterable = sensorRepository.findAll();
                    for (Sensor sensor : sensors) {
                        for (Sensor storage : sensorIterable) {
                            if (sensor.getId().equals(storage.getId())) {
                                //TODO: it is bad practice to use empty exceptions. Please, create custom exceptions with convenient exception messages.
                                // Also, such cases should be logged
                                throw new Exception();
                            }
                        }
                    }
                    sensorRepository.saveAll(sensors);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sensorRepository.findAll();
    }
}
