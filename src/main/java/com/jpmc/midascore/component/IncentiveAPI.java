package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveAPI {

    private final RestTemplate  restTemplate;

    public IncentiveAPI(RestTemplate restTemplate)
    {
        this.restTemplate= restTemplate;
    }

    public Incentive get_incentive(Transaction transaction)
    {
        return restTemplate.postForObject("http://localhost:33400/incentive",transaction,Incentive.class);
    }

}
