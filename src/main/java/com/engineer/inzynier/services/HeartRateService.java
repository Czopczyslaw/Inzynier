package com.engineer.inzynier.services;

import com.engineer.inzynier.dao.HeartRateDAO;
import com.engineer.inzynier.entities.HeartRateData;
import com.engineer.inzynier.helpers.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HeartRateService {

    @Autowired
    private HeartRateDAO heartRateDAO;

    public List<List<Object>> getJSChartData(Date date, String userUID) {
        List<List<Object>> list = new ArrayList<>();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.minusDays(7);

        for (int day = 7; day >= 1; day--) {
            List<Object> values = new ArrayList<>();

            Date searchDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date startOfDay = DateHelper.getStartOfDay(searchDate);
            Date endOfDay = DateHelper.getEndOfDay(searchDate);
            List<HeartRateData> heartRateData = heartRateDAO.getAllForPeriod(startOfDay, endOfDay, userUID);


            if (heartRateData != null && !heartRateData.isEmpty()) {
                long avgHeartRate = 0;
                for (HeartRateData heartRate : heartRateData) {
                    avgHeartRate += heartRate.getHeartRate();
                }
                avgHeartRate = avgHeartRate / heartRateData.size();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                values.add(dateFormat.format(searchDate));
                values.add(avgHeartRate);
            }

            localDate = localDate.plusDays(1);
            list.add(values);

        }

        return list;

    }
    //dla czystego kodu powinienem wydzielic pętle for do osobnej metody, by nie bylo powtarzajacego sie kodu
    //jak bedzie czas to to zrobie.
    public List<List<Object>> getCustomRangedJSChartData(Date fromDate, Date toDate, String userUID) {
        List<List<Object>> list = new ArrayList<>();
        LocalDate date = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for (; date.isBefore(endDate); date = date.plusDays(1)) {
            List<Object> values = new ArrayList<>();
            Date searchDate = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date startOfDay = DateHelper.getStartOfDay(searchDate);
            Date endOfDay = DateHelper.getEndOfDay(searchDate);
            List<HeartRateData> heartRateData = heartRateDAO.getAllForPeriod(startOfDay, endOfDay, userUID);

            if (heartRateData != null && !heartRateData.isEmpty()) {
                int avgHeartRate = 0;
                for (HeartRateData heartRate : heartRateData) {
                    avgHeartRate += heartRate.getHeartRate();
                }
                avgHeartRate = avgHeartRate / heartRateData.size();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                values.add(dateFormat.format(searchDate));
                values.add(avgHeartRate);
            }
            list.add(values);
        }
        return list;
    }

    public HeartRateData getLatestHeartRate(String userUID) {
        return heartRateDAO.getLatestHeartRateForUser(userUID);
    }

}
