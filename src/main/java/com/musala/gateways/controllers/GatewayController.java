package com.musala.gateways.controllers;

import com.musala.gateways.entities.Gateway;
import com.musala.gateways.services.GatewayService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> findALl(){
        try {
            return gatewayService.findALl();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
      }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> findById(@PathVariable String serialNumber){
        try {
            return gatewayService.findById(serialNumber);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Gateway gateway){
        try {
            return gatewayService.save(gateway);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
    }

    @PostMapping("/{serialNumber}/{uid}")
    public ResponseEntity<?> addDevice(@PathVariable String serialNumber, @PathVariable Long uid){
        try {
            return gatewayService.addDevice(serialNumber,uid);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error getting the data: "+e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something is wrong: "+e.getMessage());
        }
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<?> delete(@PathVariable String serialNumber){
       try {
           return gatewayService.delete(serialNumber);
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong: "+e.getMessage());
       }
    }


}
