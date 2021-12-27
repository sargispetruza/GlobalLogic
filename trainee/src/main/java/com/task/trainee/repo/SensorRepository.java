package com.task.trainee.repo;

import com.task.trainee.models.Sensor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, Long> {
    @Query("from Sensor where type=:selectType and battery_percentage=:selectBattery_percentage " +
            "and sensor_status.status=:selectSensor_status_status")
    List<Sensor> find(@Param("selectType") String selectType,
                      @Param("selectBattery_percentage") Long selectBattery_percentage,
                      @Param("selectSensor_status_status") String sensorStatus_status);

    @Query("from Sensor where battery_percentage=:selectBattery_percentage " +
            "and sensor_status.status=:selectSensor_status_status")
    List<Sensor> find(@Param("selectBattery_percentage") Long selectBattery_percentage,
                      @Param("selectSensor_status_status") String sensorStatus_status);

    @Query("from Sensor where type=:selectType " +
            "and sensor_status.status=:selectSensor_status_status")
    List<Sensor> find(@Param("selectType") String selectType,
                      @Param("selectSensor_status_status") String sensorStatus_status);

    @Query("from Sensor where sensor_status.status=:selectSensor_status_status")
    List<Sensor> find(@Param("selectSensor_status_status") String sensorStatus_status);
}
