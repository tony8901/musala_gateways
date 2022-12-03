package com.musala.gateways;

import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    private Long UID;
    private String vendor;
    private String dateCreated;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialGateway")
    private Gateway gateway;

    public Device() {
    }

    public Device(Long UID, String vendor, String dateCreated, boolean status, Gateway gateway) {
        this.UID = UID;
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
        this.gateway = gateway;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "Device{" +
                "UID=" + UID +
                ", vendor='" + vendor + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", status=" + status +
                '}';
    }
}
