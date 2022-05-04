package com.engineer.inzynier.restservices;

import com.engineer.inzynier.controllers.DataController;
import com.engineer.inzynier.dao.StepsDataDAO;
import com.engineer.inzynier.dto.StepsDataDTO;
import com.engineer.inzynier.entities.StepsData;
import com.engineer.inzynier.exceptions.DataRegisterException;
import com.engineer.inzynier.helpers.DateHelper;
import com.engineer.inzynier.restoutput.DataRegisteredOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Service
public class StepsRegistrationSerivce {
    Logger logger = LoggerFactory.getLogger(DataController.class);
    @Autowired
    private StepsDataDAO stepsDataDAO;

    public DataRegisteredOutput registerSteps(StepsDataDTO stepsDataDTO) {


        StepsData stepsData = new StepsData();

        stepsData.setSteps(Long.parseLong(stepsDataDTO.getStepsCount()));
        stepsData.setUserUID(stepsDataDTO.getUserUID());
        Date entryTime = DateHelper.parseDateFromISOString(stepsDataDTO.getEntryTime());
        stepsData.setEntryTime(entryTime);

        try {
            stepsDataDAO.saveSteps(stepsData);
        } catch (Exception ex) {
            throw new DataRegisterException(ex.getMessage());
        }
        return new DataRegisteredOutput("Steps registered");
    }

}


