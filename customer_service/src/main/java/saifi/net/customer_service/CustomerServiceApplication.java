package saifi.net.customer_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import saifi.net.customer_service.entities.Customer;
import saifi.net.customer_service.service.CustomerService;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService){
        return args -> {
            List<String> names = List.of("Mohamed","Imane","Yassine");
            names.forEach(name->{
                customerService.saveCustomer(Customer.builder()
                        .name(name).email(name+"@gmail.com")
                                .build());
            });
        };
    }

}
