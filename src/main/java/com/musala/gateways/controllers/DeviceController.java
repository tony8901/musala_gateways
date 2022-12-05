package com.musala.gateways.controllers;

import com.musala.gateways.entities.Device;
import com.musala.gateways.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * http://127.0.0.1:8080/api/devices
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> findAll(){
        return deviceService.findAll();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Device> findById(@PathVariable Long uid){
        return deviceService.findById(uid);
    }

    @PostMapping
    public ResponseEntity<Device> save(@RequestBody Device device){
        return deviceService.save(device);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long uid){
        return deviceService.delete(uid);
    }

}
