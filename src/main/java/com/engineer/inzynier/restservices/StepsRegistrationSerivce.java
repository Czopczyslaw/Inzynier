package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.StepsDataDAO;
import com.engineer.inzynier.dto.StepsDataDTO;
import com.engineer.inzynier.entities.StepsData;
import com.engineer.inzynier.exceptions.DataRegisterException;
import com.engineer.inzynier.restoutput.DataRegisteredOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class StepsRegistrationSerivce {

    @Autowired
    private StepsDataDAO stepsDataDAO;

    public DataRegisteredOutput registerSteps(StepsDataDTO stepsDataDTO){


        StepsData stepsData = new StepsData();

        stepsData.setSteps(Long.parseLong(stepsDataDTO.getStepsCount()));
        stepsData.setUserUID(stepsDataDTO.getUserUID());
        stepsData.setEntryTime(Date.from(Instant.now()));
        try {
            stepsDataDAO.saveSteps(stepsData);
        } catch (Exception ex) {
            throw new DataRegisterException(ex.getMessage());
        }
        return new DataRegisteredOutput("Steps registered");
    }

}
