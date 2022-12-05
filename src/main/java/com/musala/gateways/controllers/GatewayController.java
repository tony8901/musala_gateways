package com.musala.gateways.controllers;

import com.musala.gateways.entities.Gateway;
import com.musala.gateways.services.GatewayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * http://127.0.0.1:8080/api/gateways
 */
@RestController
@RequestMapping("/api/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping
    public ResponseEntity<List<Gateway>> findALl(){
        return gatewayService.findALl();
      }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<Gateway> findById(@PathVariable String serialNumber){
        return gatewayService.findById(serialNumber);
    }

    @PostMapping
    public ResponseEntity<Gateway> save(@RequestBody Gateway gateway){
        return gatewayService.save(gateway);
    }

    @PostMapping("/{serialNumber}/{uid}")
    public ResponseEntity<Gateway> addDevice(@PathVariable String serialNumber, @PathVariable Long uid){
        return gatewayService.addDevice(serialNumber,uid);
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String serialNumber){
        return gatewayService.delete(serialNumber);
    }

//    @DeleteMapping("/{serialNumber}/devices/{uid}")
//    public ResponseEntity<Gateway> deleteDevice(@PathVariable String serialNumber, @PathVariable Long uid){
//        return gatewayService.deleteDevice(serialNumber,uid);
//    }

}
