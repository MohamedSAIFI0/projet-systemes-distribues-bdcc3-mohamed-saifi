package saifi.net.ebankservice.services;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
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

    @McpTool(description = "Get All Accounts")
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "Get a bank account by id")
    public BankAccount getBankAccountById(@McpToolParam(description = "The Bank account ID") String id){
         BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
         bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));

         return bankAccount;
    }


    @McpTool(description = "Save Bank Account")
    public BankAccount saveBankAccount(@McpToolParam(description = "the bank account to save (balance, type, customerId)") BankAccount bankAccount){
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
