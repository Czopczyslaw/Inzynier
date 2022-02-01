package com.engineer.inzynier.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userUid;
    private String uid;
    private String deviceName;
    private Date deviceAddedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        if (uid != null) {
            this.uid = uid;
        } else {
            this.uid = UUID.randomUUID().toString();
        }
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getDeviceAddedDate() {
        return deviceAddedDate;
    }

    public void setDeviceAddedDate(Date deviceAddedDate) {
        if (deviceAddedDate != null) {
            this.deviceAddedDate = deviceAddedDate;
        } else {
            this.deviceAddedDate = new Date();
        }
    }
}