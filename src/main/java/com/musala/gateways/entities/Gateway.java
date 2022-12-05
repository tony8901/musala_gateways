package com.musala.gateways.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
}

