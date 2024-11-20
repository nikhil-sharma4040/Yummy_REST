package org.myprojects.yummy_rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myprojects.yummy_rest.dto.CustomerRequest;
import org.myprojects.yummy_rest.dto.CustomerResponse;
import org.myprojects.yummy_rest.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}