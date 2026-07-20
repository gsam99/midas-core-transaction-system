package com.jpmc.midascore.entity;
import jakarta.persistence.*;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue()
    private long id;

    private float amount;
    @ManyToOne
    private UserRecord sender;
    @ManyToOne
    private UserRecord receiver;

    private float incentive_amount;

    protected TransactionRecord(){}
    public TransactionRecord(UserRecord sender, UserRecord receiver, float amount, float incentive_amount)
    {
        this.sender=sender;
        this.receiver=receiver;
        this.amount=amount;
        this.incentive_amount=incentive_amount;
    }



}
