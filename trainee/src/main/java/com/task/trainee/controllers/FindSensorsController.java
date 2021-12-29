package com.task.trainee.controllers;

import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//TODO: It is not a good idea to put verbs in class names. Verbs more sufficient in method names.
@Controller
public class FindSensorsController {

    private final SensorRepository sensorRepository;

    public FindSensorsController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/")
    public String find() {
        return "find";
    }

    //TODO: it is not necessary to name method with "Post" word. We have already know http method by annotation
    //TODO: all business logic should be on the service layer
    //TODO: Please follow java naming conventions. Name your local variables to match the regular expression '^[a-z][a-zA-Z0-9]*$'.
    @PostMapping("/")
    public String findPost(Model model, @RequestParam(required = false) String type, @RequestParam(required = false) String battery_percentage,
                           @RequestParam String sensor_status) {
        List<Sensor> findedSensors = null;
        if (type.equals("") && battery_percentage.equals("")) {
            findedSensors = sensorRepository.find(sensor_status);
        } else if (type.equals("") || battery_percentage.equals("")) {
            if (type.equals(""))
                findedSensors = sensorRepository.find(Long.parseLong(battery_percentage), sensor_status);
            if (battery_percentage.equals("")) findedSensors = sensorRepository.find(type, sensor_status);
        } else findedSensors = sensorRepository.find(type, Long.parseLong(battery_percentage),
                sensor_status);
        model.addAttribute("post", findedSensors);
        return "find";
    }
}
