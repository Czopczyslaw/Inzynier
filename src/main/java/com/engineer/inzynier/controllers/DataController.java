package com.engineer.inzynier.controllers;

import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.entities.UserPrincipal;
import com.engineer.inzynier.services.StepDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    Logger logger = LoggerFactory.getLogger(DataController.class);
    @Autowired
    private StepDataService stepDataService;


    @GetMapping("/heartRateChart")
    public String getHeartRateChartData(Map<String, Object> model){
        return "heartRateChart";
    }
    @GetMapping("/stepsChart")
    public String getStepsChartData(Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String userUID = userPrincipal.getUID();

        List<List<Object>> list = stepDataService.getJSChartData(new Date(),userUID);




        model.put("chartData",list);
        return "stepsChart";
    }

}
