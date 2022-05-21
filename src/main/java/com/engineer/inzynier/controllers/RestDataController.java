package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.HeartRateDTO;
import com.engineer.inzynier.dto.StepsDataDTO;
import com.engineer.inzynier.restservices.HeartRateRegistrationService;
import com.engineer.inzynier.restservices.StepsRegistrationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class RestDataController {
    @Autowired
    private HeartRateRegistrationService heartRateRegistrationService;

    @Autowired
    private StepsRegistrationSerivce stepsRegistrationSerivce;

    @PostMapping("/api/stepRegister")
    public void registerSteps(@RequestBody StepsDataDTO dto) {
        stepsRegistrationSerivce.registerSteps(dto);
    }
    @PostMapping("/api/stepBatchRegister")
    public void registerSteps(@RequestBody StepsDataDTO[] dtos){
        for(StepsDataDTO dto: dtos){
            stepsRegistrationSerivce.batchRegisterSteps(dto);
        }
    }

    @PostMapping("/api/heartRateRegister")
    public void registerHeartRate(@RequestBody HeartRateDTO dto) {
        heartRateRegistrationService.registerHeartRate(dto);
    }

    @PostMapping("/api/heartRateBatchRegisterUnixTime")
    public void registerHeartRate(@RequestBody HeartRateDTO[] dtos) {
        for (HeartRateDTO dto:
             dtos) {
            heartRateRegistrationService.registerBatchHeartRate(dto);
        }
    }
    @GetMapping("/test")
    public Object test(){
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("123123");
        list.add("1243") ;
        list.add("1223");
        list.add("1243");
        list.add("12673");
        list.add("12343");
        return list;
    }
}
