package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.repositories.DeviceRepository;
import com.musala.gateways.repositories.GatewayRepository;
import com.musala.gateways.utils.Util;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GatewayService {

    private final GatewayRepository gatewayRepository;

    private final DeviceRepository deviceRepository;

    public GatewayService(GatewayRepository gatewayRepository, DeviceRepository deviceRepository) {
        this.gatewayRepository = gatewayRepository;
        this.deviceRepository = deviceRepository;
    }

    public ResponseEntity<?> findALl() {
        Optional<Object> optionalGateways = Optional.of(gatewayRepository.findAll());
        return optionalGateways.map(gateways -> new ResponseEntity<>(gateways, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gateways not founds!"));
    }

    public ResponseEntity<?> findById(String serialNumber){
        if(gatewayRepository.existsById(serialNumber)){
            return ResponseEntity.status(HttpStatus.OK).body(gatewayRepository.findById(serialNumber));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gateway not found with the serial number: "+serialNumber+"!");
        }
    }

    public ResponseEntity<?> save(Gateway gateway) {
        Util util = new Util();
        if (util.ipValidating(gateway.getGateway_ip())) {
            return ResponseEntity.status(HttpStatus.OK).body(gatewayRepository.save(gateway));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong data!");
    }

    public ResponseEntity<?> addDevice(String serialNumber, Long uid) {
        Gateway gateway = gatewayRepository.findById(serialNumber).orElseThrow(() -> new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber));
        Device device = deviceRepository.findById(uid).orElseThrow(() -> new EntityNotFoundException("Device not found with UID: " + uid));

        if(gateway.getGateway_devices().size() < 10) {
            device.setDevice_gateway(gateway);
            deviceRepository.save(device);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong data!");
    }

    public ResponseEntity<?> delete(String serialNumber) {
        if (!gatewayRepository.existsById(serialNumber)) {
            return new ResponseEntity<>("Gateway not found with Serial Number: " + serialNumber,HttpStatus.NOT_FOUND);
        }

        gatewayRepository.deleteById(serialNumber);
        return ResponseEntity.ok().build();
    }

}
