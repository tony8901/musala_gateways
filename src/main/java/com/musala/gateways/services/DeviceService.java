package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public ResponseEntity<List<Device>> findAll(){
        Optional<List<Device>> optionalDevices = Optional.of(deviceRepository.findAll());
        return optionalDevices.map(devices -> new ResponseEntity<>(devices, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Device> findById(Long uid){
        Optional<Device> device = deviceRepository.findById(uid);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Device> save(Device device){
        Optional<Device> optionalDevices = Optional.of(deviceRepository.save(device));
        return optionalDevices.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<HttpStatus> delete(Long uid){
        if(!deviceRepository.existsById(uid)) { throw new EntityNotFoundException("Device not found with UID: "+uid); }

        deviceRepository.deleteById(uid);
        return ResponseEntity.noContent().build();
    }


}
