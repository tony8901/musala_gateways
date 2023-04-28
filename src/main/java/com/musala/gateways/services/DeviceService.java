package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.repositories.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public ResponseEntity<?> findAll(){
        Optional<Object> optionalDevices = Optional.of(deviceRepository.findAll());
        return optionalDevices.map(devices -> new ResponseEntity<>(devices, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devices not founds!"));
    }

    public ResponseEntity<?> findById(Long uid){
        if(deviceRepository.existsById(uid)){
            return ResponseEntity.status(HttpStatus.OK).body(deviceRepository.findById(uid));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found with the uid: "+uid+"!");
        }
    }

    public ResponseEntity<?> save(Device device){
        return ResponseEntity.status(HttpStatus.OK).body(deviceRepository.save(device));
    }

    public ResponseEntity<?> delete(Long uid){
        if(!deviceRepository.existsById(uid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found with the uid: "+uid+"!");
        }

        deviceRepository.deleteById(uid);
        return ResponseEntity.ok("Device deleted! :)");
    }


}
