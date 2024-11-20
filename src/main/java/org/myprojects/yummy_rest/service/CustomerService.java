package org.myprojects.yummy_rest.service;

import lombok.RequiredArgsConstructor;
import org.myprojects.yummy_rest.dto.CustomerRequest;
import org.myprojects.yummy_rest.entity.Customer;
import org.myprojects.yummy_rest.mapper.CustomerMapper;
import org.myprojects.yummy_rest.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toCustomer(request);
        repo.save(customer);
        return "Customer created";
    }
}
