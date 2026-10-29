package saifi.net.ebankservice.services;

import org.springframework.stereotype.Service;
import saifi.net.ebankservice.entities.BankAccount;
import saifi.net.ebankservice.feign.CustomerRestClient;
import saifi.net.ebankservice.model.Customer;
import saifi.net.ebankservice.repository.BankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id){
         BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
         bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));

         return bankAccount;
    }

    public BankAccount saveBankAccount(BankAccount bankAccount){
        try{
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
