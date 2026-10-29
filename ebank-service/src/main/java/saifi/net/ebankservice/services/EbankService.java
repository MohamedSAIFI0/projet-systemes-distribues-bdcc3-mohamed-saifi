package saifi.net.ebankservice.services;

import org.springframework.stereotype.Service;
import saifi.net.ebankservice.entities.BankAccount;
import saifi.net.ebankservice.repository.BankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;

    public EbankService(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id){
        return bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));

    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }
}
