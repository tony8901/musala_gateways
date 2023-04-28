package com.musala.gateways.controllers;

import com.musala.gateways.entities.Device;
import com.musala.gateways.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<?> findAll(){
        try {
            return deviceService.findAll();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> findById(@PathVariable Long uid){
        try {
            return deviceService.findById(uid);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Device device){
        try {
            return deviceService.save(device);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong: "+e.getMessage());
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable Long uid){
       try {
           return deviceService.delete(uid);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong: "+e.getMessage());
       }
    }

}
