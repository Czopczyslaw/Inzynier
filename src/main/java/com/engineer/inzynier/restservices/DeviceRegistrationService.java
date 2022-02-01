package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.DeviceDAO;
import com.engineer.inzynier.dto.DeviceRegistrationDTO;
import com.engineer.inzynier.entities.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class DeviceRegistrationService {

    @Autowired
    private DeviceDAO deviceDAO;

    public void registerDevice(DeviceRegistrationDTO dto) {
        Device device = new Device();

        device.setDeviceName(dto.getDeviceName());
        device.setUserUid(dto.getUserUID());
        device.setUid(UUID.randomUUID().toString());
        device.setDeviceAddedDate(Date.from(Instant.now()));

        deviceDAO.addDevice(device);
    }
}
