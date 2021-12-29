package com.task.trainee.models;

import javax.persistence.*;

@Entity
@Table(schema = "json")
public class Sensor {

    //TODO: sensors already have unique id. Why do we need pk?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column(unique = true)
    private String id;
    private String name;
    private String description;
    private String type;
    //TODO: Please follow java naming conventions. Name your local variables to match the regular expression '^[a-z][a-zA-Z0-9]*$'.
    private Long battery_percentage;

    @OneToOne(cascade = CascadeType.ALL)
    private SensorStatus sensor_status;
    private String modified_time;
    private String modified_by;
    private Long created_time;
    private String created_by;

    public Sensor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getBattery_percentage() {
        return battery_percentage;
    }

    public void setBattery_percentage(Long battery_Percentage) {
        this.battery_percentage = battery_Percentage;
    }

    public SensorStatus getSensor_status() {
        return sensor_status;
    }

    public void setSensor_status(SensorStatus sensor_status) {
        this.sensor_status = sensor_status;
    }

    public String getModified_time() {
        return modified_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public Long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Long created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
