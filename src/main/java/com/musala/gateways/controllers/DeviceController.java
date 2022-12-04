package com.musala.gateways.controllers;

import com.musala.gateways.entities.Device;
import com.musala.gateways.repositories.DeviceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceController {
    DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * http://127.0.0.1:8080/api/devices
     * @return
     */
    @GetMapping("/api/devices")
    public List<Device> findAll(){
        return deviceRepository.findAll();
    }
}
