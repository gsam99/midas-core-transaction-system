package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;
import com.jpmc.midascore.component.IncentiveAPI;


@Component
public class  DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRecordRepository transactionRepository;
    private final IncentiveAPI incentiveAPI;

    public DatabaseConduit(UserRepository userRepository, TransactionRecordRepository transactionRepository, IncentiveAPI incentiveAPI) {

        this.userRepository = userRepository;
        this.transactionRepository=transactionRepository;
        this.incentiveAPI= incentiveAPI;
    }

    public void processTransaction(Transaction transaction)
    {
        UserRecord sender= userRepository.findById(transaction.getSenderId());
        UserRecord receiver= userRepository.findById(transaction.getRecipientId());

        if (sender!=null && receiver!=null && sender.getBalance()>=transaction.getAmount())
        {
            Incentive I = incentiveAPI.get_incentive(transaction);
            float sender_balance= sender.getBalance()-transaction.getAmount();
            float receiver_balance= transaction.getAmount()+ receiver.getBalance()+I.getIncentive_amount();

            sender.setBalance(sender_balance);
            receiver.setBalance(receiver_balance);

            save(sender);
            save(receiver);
            saveTransaction(new TransactionRecord(sender, receiver, transaction.getAmount(),I.getIncentive_amount()));

            System.out.println("BALANCE  " + sender.getName() + "=" + sender.getBalance()
                    + "  " + receiver.getName() + "=" + receiver.getBalance());




        }






    }
    public void saveTransaction(TransactionRecord transaction)
    {
        transactionRepository.save(transaction);
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

}
