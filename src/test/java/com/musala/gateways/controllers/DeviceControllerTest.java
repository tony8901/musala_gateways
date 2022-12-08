package com.musala.gateways.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeviceControllerTest {

    @Autowired
    private DeviceController deviceController;


    @Test
    public void contextLoad() throws Exception{
        assertThat(deviceController).isNotNull();
    }
}