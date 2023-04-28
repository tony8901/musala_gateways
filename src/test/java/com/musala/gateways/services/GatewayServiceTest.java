package com.musala.gateways.services;

import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.repositories.DeviceRepository;
import com.musala.gateways.repositories.GatewayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Clase generada por IA
 */

public class GatewayServiceTest {

    @InjectMocks
    private GatewayService gatewayService;

    @Mock
    private GatewayRepository gatewayRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Gateway> gateways = new ArrayList<>();
        gateways.add(new Gateway());
        when(gatewayRepository.findAll()).thenReturn(gateways);
        ResponseEntity<?> response = gatewayService.findALl();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gateways, response.getBody());
    }

    @Test
    public void testFindById() {
        String serialNumber = "testSerialNumber";
        Gateway gateway = new Gateway();
        when(gatewayRepository.existsById(serialNumber)).thenReturn(true);
        when(gatewayRepository.findById(serialNumber)).thenReturn(java.util.Optional.of(gateway));
        ResponseEntity<?> response = gatewayService.findById(serialNumber);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(java.util.Optional.of(gateway), response.getBody());
    }

    @Test
    public void testSave() {
        Gateway gateway = new Gateway();
        gateway.setIp("192.168.1.1");
        when(gatewayRepository.save(gateway)).thenReturn(gateway);
        ResponseEntity<?> response = gatewayService.save(gateway);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gateway, response.getBody());
    }

    @Test
    public void testAddDevice() {
        String serialNumber = "testSerialNumber";
        Long uid = 1L;
        Gateway gateway = new Gateway();
        gateway.setDevices(new ArrayList<>());
        Device device = new Device();
        when(gatewayRepository.findById(serialNumber)).thenReturn(java.util.Optional.of(gateway));
        when(deviceRepository.findById(uid)).thenReturn(java.util.Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);
        ResponseEntity<?> response = gatewayService.addDevice(serialNumber, uid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(deviceRepository).save(device);
    }

    @Test
    public void testAddDeviceGatewayNotFound() {
        String serialNumber = "testSerialNumber";
        Long uid = 1L;
        when(gatewayRepository.findById(serialNumber)).thenThrow(new EntityNotFoundException("Gateway not found with Serial Number: " + serialNumber));
        try {
            gatewayService.addDevice(serialNumber, uid);
        } catch (EntityNotFoundException e) {
            assertEquals("Gateway not found with Serial Number: " + serialNumber, e.getMessage());
        }
    }

    @Test
    public void testAddDeviceDeviceNotFound() {
        String serialNumber = "testSerialNumber";
        Long uid = 1L;
        Gateway gateway = new Gateway();
        when(gatewayRepository.findById(serialNumber)).thenReturn(java.util.Optional.of(gateway));
        when(deviceRepository.findById(uid)).thenThrow(new EntityNotFoundException("Device not found with UID: " + uid));
        try {
            gatewayService.addDevice(serialNumber, uid);
        } catch (EntityNotFoundException e) {
            assertEquals("Device not found with UID: " + uid, e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        String serialNumber = "testSerialNumber";
        when(gatewayRepository.existsById(serialNumber)).thenReturn(true);
        doNothing().when(gatewayRepository).deleteById(serialNumber);
        ResponseEntity<?> response = gatewayService.delete(serialNumber);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(gatewayRepository).deleteById(serialNumber);
    }

    @Test
    public void testDeleteGatewayNotFound() {
        String serialNumber = "testSerialNumber";
        when(gatewayRepository.existsById(serialNumber)).thenReturn(false);
        ResponseEntity<?> response = gatewayService.delete(serialNumber);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Gateway not found with Serial Number: " + serialNumber, response.getBody());
    }
}
