package org.example.module19.mapper;

import org.example.module19.model.Order;
import org.example.module19.model.dto.request.OrderCreateRequest;
import org.example.module19.model.dto.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    OrderResponse toOrderResponse(Order order);

    Order toOrder(OrderCreateRequest createRequest);
}