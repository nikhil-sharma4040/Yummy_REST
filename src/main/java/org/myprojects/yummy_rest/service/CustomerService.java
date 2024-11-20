package org.myprojects.yummy_rest.service;

import lombok.RequiredArgsConstructor;
import org.myprojects.yummy_rest.dto.CustomerRequest;
import org.myprojects.yummy_rest.dto.CustomerResponse;
import org.myprojects.yummy_rest.entity.Customer;
import org.myprojects.yummy_rest.exception.CustomerNotFoundException;
import org.myprojects.yummy_rest.helper.EncryptionService;
import org.myprojects.yummy_rest.mapper.CustomerMapper;
import org.myprojects.yummy_rest.repo.CustomerRepo;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final CustomerRepo customerRepo;
    private final EncryptionService encryptionService;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));

        repo.save(customer);
        return "Customer created";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
            .orElseThrow(() -> new CustomerNotFoundException(
                format("No customer found with provided email: %s", email)
            ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return mapper.toCustomerResponse(customer);
    }
}
