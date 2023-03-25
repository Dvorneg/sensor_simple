package ru.denis.sensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.sensor.dto.MeasurementDTO;
import ru.denis.sensor.dto.MeasurementsResponse;
import ru.denis.sensor.models.Measurement;
import ru.denis.sensor.services.MeasurementServices;
import ru.denis.sensor.util.MeasurementErrorResponse;
import ru.denis.sensor.util.MeasurementException;
import ru.denis.sensor.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.denis.sensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementServices measurementServices;
    private final MeasurementValidator measurementValidator ;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementServices measurementServices, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementServices = measurementServices;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurementToAdd = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd,bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementServices.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);  //<> ok status 200

    }

    @GetMapping()
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementServices.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList())) ;  //Jackson -> JSON
    }

    @GetMapping ("/rainyDaysCount")
    public Long getRainyDaysCount(){
        return measurementServices.findAll().stream().filter(Measurement::isRaining).count();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){

        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );  //400, BAD_REQUEST
    }




}
