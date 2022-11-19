package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.DateRangeDTO;
import com.engineer.inzynier.entities.HeartRateData;
import com.engineer.inzynier.entities.StepsData;
import com.engineer.inzynier.entities.UserPrincipal;
import com.engineer.inzynier.helpers.DateHelper;
import com.engineer.inzynier.services.HeartRateService;
import com.engineer.inzynier.services.StepDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    @Autowired
    private StepDataService stepDataService;
    @Autowired
    private HeartRateService heartRateService;


    @GetMapping("/heartRateChart")
    public String getHeartRateChartData(Map<String, Object> model) {
        String userUID = getUserUID();

        List<List<Object>> list = heartRateService.getJSChartData(new Date(), userUID);
        model.put("chartData", list);

        HeartRateData latestHeartRate = heartRateService.getLatestHeartRate(userUID);
        model.put("heartRate", latestHeartRate);
        return "heartRateChart";
    }

    @GetMapping("/rangedStepsChart")
    public String getRangedStepsChart(Map<String, Object> model) {
        DateRangeDTO dto = new DateRangeDTO();
        LocalDate dateFrom = LocalDate.now().minusDays(7);
        LocalDate dateTo = LocalDate.now();
        dto.setFromDate(dateFrom.toString());
        dto.setToDate(dateTo.toString());
        model.put("form", dto);
        return "rangedStepsChart";
    }

    @PostMapping("/rangedStepsChart")
    public String getRangedSteps(@ModelAttribute DateRangeDTO dto, Map<String, Object> model) {
        String userUID = getUserUID();

        LocalDate fromDate = LocalDate.parse(dto.getFromDate());
        LocalDate toDate = LocalDate.parse(dto.getToDate());
        Date dateFrom = DateHelper.parseLocalDateToDate(fromDate);
        Date dateTo = DateHelper.parseLocalDateToDate(toDate);


        List<List<Object>> list = heartRateService.getCustomRangedJSChartData(dateFrom, dateTo, userUID);
        model.put("chartData", list);
        model.put("form", dto);
        return "rangedHeartRateChart";
    }

    @PostMapping("/rangedHeartRateChart")
    public String getRangedHeartRateChart(@ModelAttribute DateRangeDTO dto, Map<String, Object> model) {
        String userUID = getUserUID();

        LocalDate fromDate = LocalDate.parse(dto.getFromDate());
        LocalDate toDate = LocalDate.parse(dto.getToDate());
        Date dateFrom = DateHelper.parseLocalDateToDate(fromDate);
        Date dateTo = DateHelper.parseLocalDateToDate(toDate);


        List<List<Object>> list = heartRateService.getCustomRangedJSChartData(dateFrom, dateTo, userUID);
        model.put("chartData", list);
        model.put("form", dto);
        return "rangedHeartRateChart";
    }

    @GetMapping("/rangedHeartRateChart")
    public String getRangedHeartRate(Map<String, Object> model) {
        DateRangeDTO dto = new DateRangeDTO();
        LocalDate dateFrom = LocalDate.now().minusDays(7);
        LocalDate dateTo = LocalDate.now();
        dto.setFromDate(dateFrom.toString());
        dto.setToDate(dateTo.toString());
        model.put("form", dto);

        return "rangedHeartRateChart";
    }

    @GetMapping("/stepsChart")
    public String getStepsChartData(Map<String, Object> model) {
        String userUID = getUserUID();

        List<List<Object>> list = stepDataService.getJSChartData(new Date(), userUID);

        model.put("chartData", list);

        StepsData latestStepsData = stepDataService.getLatestStepsData(userUID);
        model.put("steps", latestStepsData);

        return "stepsChart";
    }

    private String getUserUID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUID();
    }
}
