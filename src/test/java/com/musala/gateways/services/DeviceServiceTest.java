package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Clase generada por IA
 */

public class DeviceServiceTest {

    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deviceService = new DeviceService(deviceRepository);
    }

    @Test
    public void testFindAll() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());

        when(deviceRepository.findAll()).thenReturn(devices);

        ResponseEntity<?> response = deviceService.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testFindById() {
        Long uid = 1L;
        Device device = new Device();
        device.setId(uid);

        when(deviceRepository.existsById(uid)).thenReturn(true);
        when(deviceRepository.findById(uid)).thenReturn(Optional.of(device));

        ResponseEntity<?> response = deviceService.findById(uid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(device), response.getBody());

    }

    @Test
    public void testSave() {
        Device device = new Device();

        when(deviceRepository.save(device)).thenReturn(device);

        ResponseEntity<?> response = deviceService.save(device);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    public void testDeleteDeviceExists() {
        Long uid = 1L;

        when(deviceRepository.existsById(uid)).thenReturn(true);

        ResponseEntity<?> response = deviceService.delete(uid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteDeviceNotExists() {
        Long uid = 1L;

        when(deviceRepository.existsById(uid)).thenReturn(false);

        ResponseEntity<?> response = deviceService.delete(uid);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found with the uid: " + uid + "!", response.getBody());
    }
}