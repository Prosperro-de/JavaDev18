package org.example.module18.mapper;

import org.example.module18.model.Customer;
import org.example.module18.model.dto.request.CustomerCreateRequest;
import org.example.module18.model.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "dateOfBirth", source = "customerDetails.dateOfBirth")
    @Mapping(target = "loyaltyPoints", source = "customerDetails.loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "customerDetails.dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "customerDetails.loyaltyPoints", ignore = true)
    Customer toCustomer(CustomerCreateRequest createRequest);

    @Mapping(target = "dateOfBirth", source = "customerDetails.dateOfBirth")
    @Mapping(target = "loyaltyPoints", source = "customerDetails.loyaltyPoints")
    List<CustomerResponse> toCustomerResponseList(List<Customer> customers);
}
