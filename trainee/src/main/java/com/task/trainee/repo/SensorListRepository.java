package com.task.trainee.repo;

import java.net.URL;
import java.util.List;

//TODO: It is not repository since it is implemented by service class.
// I think it is better to rename this interface and put it to the service layer
public interface SensorListRepository {
    List<URL> getUrlList();
}
