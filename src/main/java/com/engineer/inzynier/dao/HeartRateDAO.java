package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.HeartRateData;
import com.engineer.inzynier.repositories.HeartRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HeartRateDAO {

    @Autowired
    HeartRateRepository heartRateRepository;


    public HeartRateData getLatestHeartRateForUser(String userUID) {
        return heartRateRepository.findFirstByUserUIDOrderByEntryTimeDesc(userUID);
    }

    public List<HeartRateData> getAllForPeriod(Date entryTimeAfter, Date entryTimeBefore, String userUID) {
        return heartRateRepository.findAllByEntryTimeBetweenAndUserUID(entryTimeAfter, entryTimeBefore, userUID);
    }

    public HeartRateData getHeartRateForDate(Date entryTimeBefore, String userUID) {
        return heartRateRepository.findFirstByEntryTimeBeforeAndAndUserUID(entryTimeBefore, userUID);
    }

    public void saveHeartRate(HeartRateData heartRateData) {
        heartRateRepository.save(heartRateData);
    }


}
