package org.example.module16.mapper;

import org.example.module16.model.Customer;
import org.example.module16.model.dto.CustomerCreateRequest;
import org.example.module16.model.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "dateOfBirth", source = "customerDetails.dateOfBirth")
    @Mapping(target = "loyaltyPoints", source = "customerDetails.loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "customerDetails.dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "customerDetails.loyaltyPoints", ignore = true)
    Customer toCustomer(CustomerCreateRequest createRequest);
}
