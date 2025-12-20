package com.swsisolutions.compundinterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/calculate-interest")
    public String calculate(
            @RequestParam("principal") double principal,
            @RequestParam("years") int years) {

        double interest = interestService.calculateCompoundInterest(principal, years);
        double rate = interestService.getInterestConfig().getRate();

        return String.format("Using interest rate of %.2f%%: Compound interest for Principal $%.2f over %d years is $%.2f",
                rate, principal, years, interest);
    }
}
