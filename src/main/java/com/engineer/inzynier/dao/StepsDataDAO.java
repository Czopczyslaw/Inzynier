package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.StepsData;
import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.repositories.StepsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StepsDataDAO {
    @Autowired
    StepsDataRepository stepsDataRepository;

    public StepsData getLatestStepsDataForUser(String userUID) {
        return stepsDataRepository.findFirstByUserUID(userUID);
    }

    public List<StepsData> getAllForPeriod(Date entryTimeAfter, Date entryTimeBefore, String userUID) {
        return stepsDataRepository.findAllByEntryTimeAfterAndEntryTimeBeforeAndUserUID(entryTimeAfter, entryTimeBefore, userUID);
    }

    public StepsData getStepsForDate(Date entryTimeBefore, String userUID) {
        return stepsDataRepository.findFirstByEntryTimeBeforeAndAndUserUID(entryTimeBefore, userUID);
    }
    public void saveSteps(StepsData stepsData){
        stepsDataRepository.save(stepsData);
    }


}
