package com.task.trainee;

import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorRepoTests {
    @Autowired
    private SensorRepository sensorRepository;
    @Test
    public void findByThreeParam() {
        List<Sensor> sensors = sensorRepository.find("Door", (long) 100, "Online");
        assertThat(sensors).hasSize(1);
    }

    @Test
    public void findByTwoParam() {
        List<Sensor> sensors = sensorRepository.find((long) 100, "Online");
        assertThat(sensors).hasSize(1);
    }

    @Test
    public void findByOneParam() {
        List<Sensor> sensors = sensorRepository.find("Online");
        assertThat(sensors).hasSize(4);
    }
}
