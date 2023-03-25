package ru.denis.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.sensor.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    //Integer findAllByRainingTrue();
    Integer countAllByRainingTrue();
}
