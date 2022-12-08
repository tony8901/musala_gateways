package com.musala.gateways.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "com_musala_gateway")
public class Gateway {

    @Id
    private String serialNumber;
    private String name;
    private String ip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gateway", cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Gateway() {
    }

    public Gateway(String serialNumber, String name, String ip) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.ip = ip;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

