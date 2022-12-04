package com.musala.gateways.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "com_musala_device")
public class Device {

    @Id
    private Long UID;
    private String vendor;
    private LocalDate dateCreated;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialGateway")
    private Gateway gateway;

    public Device() {
    }

    public Device(Long UID, String vendor, LocalDate dateCreated, boolean status, Gateway gateway) {
        this.UID = UID;
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
        this.gateway = gateway;
    }

    public Long getUID() {
        return UID;
    }

    public String getVendor() {
        return vendor;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public boolean isStatus() {
        return status;
    }

}
