package saifi.net.ebankservice.controllers;

import org.springframework.web.bind.annotation.*;
import saifi.net.ebankservice.entities.BankAccount;
import saifi.net.ebankservice.services.EbankService;

import java.util.List;

@RestController
public class EbankRestController {

    private EbankService ebankService;

    public EbankRestController(EbankService ebankService){
        this.ebankService = ebankService;
    }

    @GetMapping("/BankAccount")
    public List<BankAccount> getAllBankAccounts(){
        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/BankAccount/{id}")
    public BankAccount getBankAccountById(@PathVariable String id){
        return ebankService.getBankAccountById(id);
    }

    @PostMapping("/BankAccount")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount){
        return ebankService.saveBankAccount(bankAccount);
    }


}
