package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.HeartRateDAO;
import com.engineer.inzynier.dto.HeartRateDTO;
import com.engineer.inzynier.entities.HeartRateData;
import com.engineer.inzynier.exceptions.DataRegisterException;
import com.engineer.inzynier.restoutput.DataRegisteredOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
@Service
public class HeartRateRegistrationService {
    @Autowired
    private HeartRateDAO heartRateDAO;

    public DataRegisteredOutput registerHeartRate(HeartRateDTO heartRateDTO) {
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.setHeartRate(Long.parseLong(heartRateDTO.getHeartRate()));
        heartRateData.setUserUID(heartRateDTO.getUserUID());
        heartRateData.setEntryTime(Date.from(Instant.now()));
        try {
            heartRateDAO.saveHeartRate(heartRateData);
        } catch (Exception ex) {
            throw new DataRegisterException(ex.getMessage());
        }
        return new DataRegisteredOutput("HeartRate registered");
    }
}
