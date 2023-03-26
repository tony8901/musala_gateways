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
import com.musala.gateways.controllers.DeviceController;
import com.musala.gateways.entities.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WebMvcTest(DeviceController.class)
public class DeviceServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceService service;

    private Device device;

    private String url = "http://127.0.0.1:8080/api/devices";

    @BeforeEach
    public void init(){
        LocalDate date = LocalDate.of(2022,12,1);
        device = new Device(1L,"mockitoVendor", date, true);
    }

    @Test
    public void findAllDevices() throws Exception{
        this.mockMvc.perform(get(url)).andExpect(status().isOk());
    }
//
//    @Test
//    public void findByIdDevice() throws Exception{
//        when(service.findById(device.getDevice_id())).thenReturn(ResponseEntity.ok(device));
//
//        this.mockMvc.perform(get(url+"/{uid}", device.getDevice_id()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.uid", is(device.getDevice_id().intValue())))
//                .andExpect(jsonPath("$.vendor", is(device.getDevice_vendor())))
//                .andExpect(jsonPath("$.dateCreated", is(device.getDevice_date_created().toString())))
//                .andExpect(jsonPath("$.status", is(device.isDevice_status())));
//    }

//    @Test
//    public void saveDevice() throws Exception{
//        when(service.save(any(Device.class))).thenReturn(new ResponseEntity<>(device, HttpStatus.CREATED));
//
//        this.mockMvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(device)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.uid", is(device.getDevice_id().intValue())))
//                .andExpect(jsonPath("$.vendor", is(device.getDevice_vendor())))
//                .andExpect(jsonPath("$.dateCreated", is(device.getDevice_date_created().toString())))
//                .andExpect(jsonPath("$.status", is(device.isDevice_status())));
//    }

    @Test
    public void deleteDevice() throws Exception{
        when(service.delete(device.getDevice_id())).thenReturn(ResponseEntity.noContent().build());

        this.mockMvc.perform(delete(url+"/{uid}", device.getDevice_id()))
                .andExpect(status().isNoContent());
    }

}
