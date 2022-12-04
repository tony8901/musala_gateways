package com.musala.gateways.controllers;

import com.musala.gateways.entities.Gateway;
import com.musala.gateways.repositories.GatewayRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GatewayController {

    private GatewayRepository gatewayRepository;

    public GatewayController(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    /**
     * http://127.0.0.1:8080
     * @return
     */
    @GetMapping("/api/gateways")
    public List<Gateway> findALl(){
        System.out.println(gatewayRepository.findAll());
        return gatewayRepository.findAll();
    }

    @GetMapping("/api/gateways/{serialNumber}")
    public Gateway findById(@PathVariable String serialNumber){
        Optional<Gateway> gateway = gatewayRepository.findById(serialNumber);
        return gateway.orElse(null);
    }
}
