package org.example.module15.mapper;

import org.example.module15.model.Customer;
import org.example.module15.model.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "dateOfBirth", source = "customerDetails.dateOfBirth")
    @Mapping(target = "loyaltyPoints", source = "customerDetails.loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);
}
