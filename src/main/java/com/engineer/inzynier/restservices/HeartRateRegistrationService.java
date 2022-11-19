package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.HeartRateDAO;
import com.engineer.inzynier.dto.HeartRateDTO;
import com.engineer.inzynier.entities.HeartRateData;
import com.engineer.inzynier.exceptions.DataRegisterException;
import com.engineer.inzynier.restoutput.DataRegisteredOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
@Service
public class HeartRateRegistrationService {
    @Autowired
    private HeartRateDAO heartRateDAO;

    public DataRegisteredOutput registerHeartRate(HeartRateDTO heartRateDTO) {
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.setHeartRate(Long.parseLong(heartRateDTO.getHeartRate()));
        heartRateData.setUserUID(heartRateDTO.getUserUID());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date entryTime = null;
        try {
            entryTime =  dateFormat.parse(heartRateDTO.getEntryTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        heartRateData.setEntryTime(entryTime);
        try {
            heartRateDAO.saveHeartRate(heartRateData);
        } catch (Exception ex) {
            throw new DataRegisterException(ex.getMessage());
        }
        return new DataRegisteredOutput("HeartRate registered");
    }
    public DataRegisteredOutput registerBatchHeartRate(HeartRateDTO heartRateDTO){
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.setHeartRate(Long.parseLong(heartRateDTO.getHeartRate()));
        heartRateData.setUserUID(heartRateDTO.getUserUID());
        Long timestamp = Long.parseLong(heartRateDTO.getEntryTime());
        Instant instant = Instant.ofEpochSecond(timestamp);
        heartRateData.setEntryTime(Date.from(instant));
        try {
            heartRateDAO.saveHeartRate(heartRateData);
        } catch (Exception ex) {
            throw new DataRegisterException(ex.getMessage());
        }
        return new DataRegisteredOutput("HeartRate registered");
    }
}
