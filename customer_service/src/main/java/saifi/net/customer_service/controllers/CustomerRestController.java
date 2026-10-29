package saifi.net.customer_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import saifi.net.customer_service.entities.Customer;
import saifi.net.customer_service.repository.CustomerRepository;
import saifi.net.customer_service.service.CustomerService;

import java.util.List;

@RestController
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
}
