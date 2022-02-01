package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.Device;
import com.engineer.inzynier.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceDAO {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getDevicesList() {
        return deviceRepository.findAll();
    }

    public List<Device> getDeviceByUserUid(String userUid) {
        return deviceRepository.findAllByUserUid(userUid);
    }

    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    public Device getDeviceByUid(String uid) {
        return deviceRepository.findByUid(uid);
    }
}
