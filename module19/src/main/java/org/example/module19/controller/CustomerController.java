package org.example.module19.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.example.module19.annotation.TimeMetric;
import org.example.module19.model.dto.request.CustomerUpdateRequest;
import org.example.module19.model.dto.response.CustomerResponse;
import org.example.module19.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@Tags(value = {
        @Tag(name = "Customer controller", description = "Provides operations for managing customers")
})
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Find customer by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = "application/json",
                                    contentSchema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Customer not found")
            }
    )
    @TimeMetric
    @GetMapping("/{id}") // http://localhost:8080/api/v1/customers/1
//    @PreAuthorize("@securityService.isUserMatchesPrincipal(#id)")
    public CustomerResponse findById(@PathVariable Long id) {
         return customerService.findById(id);
    }

//    @GetMapping()// http://localhost:8080/api/v1/customers
//    public List<CustomerResponse> findAll() {
//        return customerService.findAll();
//    }

//    @GetMapping// http://localhost:8080/api/v1/customers?email=
//    public CustomerResponse findByEmail(@RequestParam String email) {
//        return customerService.findByEmail(email);
//    }

//    @GetMapping// http://localhost:8080/api/v1/customers?lastName=
//    public List<CustomerResponse> findByLastName(@RequestParam String lastName) {
//        return customerService.findForLastName(lastName);
//    }

    @PutMapping("/{id}") // http://localhost:8080/api/v1/customers/1
    @PreAuthorize("@securityService.isUserMatchesPrincipal(#id)")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @TimeMetric
    @GetMapping
    public Page<CustomerResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return customerService.findAll(pageRequest);
    }
}
