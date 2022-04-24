package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.StepsDataDTO;
import com.engineer.inzynier.restservices.StepsRegistrationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StepDataController {

    @Autowired
    private StepsRegistrationSerivce stepsRegistrationSerivce;

    @Autowired
    private Environment env;

    @PostMapping("/api/stepRegister")
    public void registerSteps(@RequestBody StepsDataDTO dto){
        stepsRegistrationSerivce.registerSteps(dto);
    }
}