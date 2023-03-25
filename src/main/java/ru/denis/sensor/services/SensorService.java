package ru.denis.sensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.sensor.models.Sensor;
import ru.denis.sensor.repository.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> findByName(String name){
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void register(Sensor sensor){
        sensorsRepository.save(sensor);
    }


}
