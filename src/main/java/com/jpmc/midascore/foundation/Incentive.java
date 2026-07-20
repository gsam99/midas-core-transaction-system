package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Incentive {

    private float incentive_amount;

    public Incentive() {}

    public Incentive(float incentive_amount)
    {
        this.incentive_amount=incentive_amount;
    }

    public float getIncentive_amount()
    {
        return incentive_amount;
    }

    public void setIncentive_amount(float incentive_amount)
    {
        this.incentive_amount=incentive_amount;
    }
}
