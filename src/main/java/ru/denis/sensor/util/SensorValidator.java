package ru.denis.sensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.denis.sensor.models.Sensor;
import ru.denis.sensor.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor=(Sensor) o;

        if (sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name","","Сенсор с таким именем уже существует!");
        }

    }
}
