package com.musala.gateways.controllers;

import com.musala.gateways.entities.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Clase adaptada de Spring Academy
 * https://spring.academy/courses/building-a-rest-api-with-spring-boot/lessons/first-rest-endpoint-lab/lab
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringAcademyTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnWhenDeviceIsSaved(){
        ResponseEntity<Device> response = testRestTemplate.getForEntity("/api/devices/123456", Device.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
