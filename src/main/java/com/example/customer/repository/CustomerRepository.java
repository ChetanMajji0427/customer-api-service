
package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByEmail(String email);
}


// File: CustomerService.java
package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return repository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(UUID id, Customer updated) {
        return repository.findById(id).map(customer -> {
            customer.setFirstName(updated.getFirstName());
            customer.setMiddleName(updated.getMiddleName());
            customer.setLastName(updated.getLastName());
            customer.setEmail(updated.getEmail());
            customer.setPhoneNumber(updated.getPhoneNumber());
            return repository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }
}
