package ru.denis.sensor.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class MeasurementDTO {

    @NotNull
    @Min(value = -100, message = "Значение должно быть больше -100!")
    @Max(value =100, message = "Значение должно быть меньше 100!")
    private Double value;

    @NotNull
    private boolean raining;

    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "raining=" + raining +
                ", value=" + value +
               // ", sensor=" + sensor +
                '}';
    }
}
