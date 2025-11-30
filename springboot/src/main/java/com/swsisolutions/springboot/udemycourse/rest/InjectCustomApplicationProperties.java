package com.swsisolutions.springboot.udemycourse.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectCustomApplicationProperties {

    // Inject Properties for coach.name and team.name

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // expose /hello return Hello Spring Boot
    @GetMapping("/printCoachName")
    public String sayHello() {
        return coachName;
    }

    @GetMapping("/printTeamName")
    public String sayHi() {
        return teamName;
    }
}
