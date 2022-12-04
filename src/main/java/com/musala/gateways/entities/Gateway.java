package com.musala.gateways.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "com_musala_gateway")
public class Gateway {

    @Id
    private String serialNumber;
    private String name;
    private String ip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gateway", cascade = CascadeType.ALL)
    List<Device> devices;

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

    public List<Device> getDevices() {
        return devices;
    }
}
