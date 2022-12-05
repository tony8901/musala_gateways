package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.repositories.DeviceRepository;
import com.musala.gateways.repositories.GatewayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatewayService {

    private final GatewayRepository gatewayRepository;

    private final DeviceRepository deviceRepository;

    public GatewayService(GatewayRepository gatewayRepository, DeviceRepository deviceRepository) {
        this.gatewayRepository = gatewayRepository;
        this.deviceRepository = deviceRepository;
    }

    public ResponseEntity<List<Gateway>> findALl(){
        Optional<List<Gateway>> optionalGateways = Optional.of(gatewayRepository.findAll());
        return optionalGateways.map(gateways -> new ResponseEntity<>(gateways, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Gateway> findById(String serialNumber){
        return ResponseEntity.ok().body(gatewayRepository.findById(serialNumber).orElseThrow(() -> new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber)));
    }

    public ResponseEntity<Gateway> save(Gateway gateway){
        if(ipValidating(gateway.getIp())) {
            Optional<Gateway> optionalGateway = Optional.of(gatewayRepository.save(gateway));
            return optionalGateway.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Gateway> addDevice(String serialNumber,Long uid){
        Gateway gateway = gatewayRepository.findById(serialNumber).orElseThrow(()-> new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber));
        Device device = deviceRepository.findById(uid).orElseThrow(()-> new EntityNotFoundException("Device not found with UID: "+uid));
        device.setGateway(gateway);
        deviceRepository.save(device);
        return ResponseEntity.ok(gateway);
    }

    public ResponseEntity<HttpStatus> delete(String serialNumber){
        if(!gatewayRepository.existsById(serialNumber)) { throw new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber); }

        gatewayRepository.deleteById(serialNumber);
        return ResponseEntity.noContent().build();
    }

//    public ResponseEntity<Gateway> deleteDevice(String serialNumber, Long uid){
//        if(!gatewayRepository.existsById(serialNumber)) { throw new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber); }
//        if(!deviceRepository.existsById(uid)) { throw new EntityNotFoundException("Device not found with UID: "+uid); }
//
//        deviceRepository.deleteById(uid);
//        Optional<Gateway> gateway = gatewayRepository.findById(serialNumber);
//        return ResponseEntity.ok(gateway.get());
//    }

    public boolean ipValidating(String ip){
        boolean b = true;
        String[] strings = ip.split("\\.");
        if(strings.length != 4) { b = false; }
        for (String s:strings){
            int i = Integer.parseInt(s);
            if(i < 0  || i > 255){ b = false; }
        }
        return b;
    }
}
