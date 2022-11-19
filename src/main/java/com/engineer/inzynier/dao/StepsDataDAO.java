package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.StepsData;
import com.engineer.inzynier.helpers.DateHelper;
import com.engineer.inzynier.repositories.StepsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class StepsDataDAO {
    @Autowired
    StepsDataRepository stepsDataRepository;

    public StepsData getLatestStepsDataForUser(String userUID) {
        return stepsDataRepository.findFirstByUserUIDOrderByEntryTimeDesc(userUID);
    }

    public List<StepsData> getAllForPeriod(Date entryTimeAfter, Date entryTimeBefore, String userUID) {
        return stepsDataRepository.findAllByEntryTimeBetweenAndUserUID(entryTimeAfter, entryTimeBefore, userUID);
    }

    public StepsData getStepsForDate(Date entryTime, String userUID) {
        return stepsDataRepository.findFirstForDayAndUserUID(userUID, DateHelper.getStartOfDay(entryTime),DateHelper.getEndOfDay(entryTime));
    }
    public void saveSteps(StepsData stepsData){
        stepsDataRepository.save(stepsData);
    }


}
