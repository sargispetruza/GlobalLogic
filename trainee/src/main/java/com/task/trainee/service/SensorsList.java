package com.task.trainee.service;

import com.task.trainee.repo.SensorListRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SensorsList implements SensorListRepository {
    private List<URL> urlList = new ArrayList<>(Arrays.asList(
            this.getClass().getClassLoader().getResource("sensors/broken_sensor_11111.json"),
            this.getClass().getClassLoader().getResource("sensors/door_sensor_0a4b2386.json"),
            this.getClass().getClassLoader().getResource("sensors/door_sensor_1b9b2380.json"),
            this.getClass().getClassLoader().getResource("sensors/door_sensor_with_wrong_email_0p0p2380.json"),
            this.getClass().getClassLoader().getResource("sensors/lite_sensor_1b5d3256.json"),
            this.getClass().getClassLoader().getResource("sensors/lite_sensor_3b6o4254.json"),
            this.getClass().getClassLoader().getResource("sensors/lite_sensor_5y6o4254.json"),
            this.getClass().getClassLoader().getResource("sensors/window_sensor_8j9f3259.json"),
            this.getClass().getClassLoader().getResource("sensors/window_sensor_with_wrong_fields_2c5d3259.json")
    )
    );

    @Override
    public List<URL> getUrlList() {
        return urlList;
    }
}
