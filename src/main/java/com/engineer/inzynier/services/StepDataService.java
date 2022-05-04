package com.engineer.inzynier.services;

import com.engineer.inzynier.dao.StepsDataDAO;
import com.engineer.inzynier.entities.StepsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StepDataService {

    @Autowired
    private StepsDataDAO stepsDataDAO;

    public List<List<Object>> getJSChartData(Date date, String userUID) {
        //Map<Object, Object> map = new HashMap<>();
        List<List<Object>> list = new ArrayList<>();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.minusDays(7);

        for (int day = 7; day >= 1; day--) {
            List<Object> values = new ArrayList<>();

            Date searchDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            StepsData stepsData = stepsDataDAO.getStepsForDate(searchDate, userUID);

            if (stepsData != null) {
                //map.put(stepsData.getEntryTime(), stepsData.getSteps());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                values.add(dateFormat.format(stepsData.getEntryTime()));
                values.add(stepsData.getSteps());
            }

            localDate = localDate.plusDays(1);
            list.add(values);

        }

        return list;

    }
}