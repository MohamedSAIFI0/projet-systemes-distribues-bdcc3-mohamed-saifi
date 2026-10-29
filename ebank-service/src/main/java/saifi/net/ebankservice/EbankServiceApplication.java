package saifi.net.ebankservice;

import org.apache.hc.core5.reactor.Command;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import saifi.net.ebankservice.entities.BankAccount;
import saifi.net.ebankservice.services.EbankService;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EbankService ebankService){
        return args -> {
            for(int i = 1; i <= 3; i++ ){
                for(int j=0; j< 5; j++){
                    ebankService.saveBankAccount(BankAccount.builder()
                                    .type(Math.random()>0.5 ? "CURRENT-ACCOUNT":"SAVING-ACCOUNT")
                                    .balance(1000+Math.random()*600000)
                                    .customerId(i)
                            .build());
                }
            }
        };
    }

}
