package com.musala.gateways.services;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gateways.controllers.GatewayController;
import com.musala.gateways.entities.Gateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GatewayController.class)
public class GatewayServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GatewayService service;

    Gateway gateway;

    @BeforeEach
    public void init(){
        gateway = new Gateway("mockitoSerial","mockitoName","0.0.0.0");
    }

    private String url= "/api/gateways";

    @Test
    public void findAllGateways() throws Exception {
        this.mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void findByIdGateway() throws Exception{
        when(service.findById(gateway.getSerialNumber())).thenReturn(ResponseEntity.ok(gateway));
        this.mockMvc.perform(get(url+"/{serialNumber}",gateway.getSerialNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ip",is(gateway.getIp())))
                .andExpect(jsonPath("$.name",is(gateway.getName())));
    }

    @Test
    public void saveGateway() throws Exception{
        when(service.save(any(Gateway.class))).thenReturn(new ResponseEntity<>(gateway, HttpStatus.CREATED));

        this.mockMvc.perform(post(url)
                .contentType((MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(gateway)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber", is(gateway.getSerialNumber())))
                .andExpect(jsonPath("$.name", is(gateway.getName())))
                .andExpect(jsonPath("$.ip", is(gateway.getIp())));
    }

    @Test
    public void deleteGateway() throws Exception{
        when(service.delete(gateway.getSerialNumber())).thenReturn(ResponseEntity.noContent().build());

        this.mockMvc.perform(delete(url+"/{serialNumber}", gateway.getSerialNumber()))
                .andExpect(status().isNoContent());
    }





}
