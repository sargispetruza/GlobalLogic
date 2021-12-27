package com.task.trainee;

import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorListRepository;
import com.task.trainee.repo.SensorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorListRepoTest {
    @Autowired
    private SensorListRepository sensorListRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    public void findAllSensors() {
        List<URL> urlList = sensorListRepository.getUrlList();
        assertThat(urlList).hasSize(9);
    }

    @Test
    public void findBrokenSensors() {
        List<URL> urlList = sensorListRepository.getUrlList();

        List<Sensor> sensors = (List<Sensor>) sensorRepository.findAll();

        Assert.assertEquals(3, urlList.size()-sensors.size());
    }
}
