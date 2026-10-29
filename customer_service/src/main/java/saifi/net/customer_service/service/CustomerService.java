package saifi.net.customer_service.service;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;
import saifi.net.customer_service.entities.Customer;
import saifi.net.customer_service.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get All customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by id")
    public Customer findCustomerById(@McpToolParam(description = "The customer id") Long id){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));
    }

    @McpTool(description = "Save a new customer")
    public Customer saveCustomer(@McpToolParam(description = "The customer to save(name, email)") Customer customer){
        return customerRepository.save(customer);
    }



}
