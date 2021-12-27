package com.task.trainee.models;

import javax.persistence.*;

@Entity
@Table(name = "sensor_status", schema = "json")
public class SensorStatus {
    @Id
    @GeneratedValue
    private Long id;

    private String status;
    private String value;

    @OneToOne(mappedBy = "sensor_status")
    private Sensor sensor;

    public SensorStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
