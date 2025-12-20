package com.swsisolutions.compundinterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    private final InterestConfig interestConfig;

    public InterestConfig getInterestConfig() {
        return interestConfig;
    }

    @Autowired
    public InterestService(InterestConfig interestConfig) {
        this.interestConfig = interestConfig;
    }

    public double calculateCompoundInterest(double principal, int years) {
        // A = P(1 + r/n)^(nt)
        // Assuming compounding is annual (n=1)
        double rate = interestConfig.getRate();
        double amount = principal * Math.pow((1 + rate / 100), years);
        // Return the interest amount (Total Amount - Principal)
        return amount - principal;
    }
}