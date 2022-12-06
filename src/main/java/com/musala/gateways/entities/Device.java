package com.musala.gateways.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "gateway_serial_number")
    @JsonIgnore
    private Gateway gateway;

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Device() {
    }

    public Device(Long UID, String vendor, LocalDate dateCreated, boolean status) {
        this.UID = UID;
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
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

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
