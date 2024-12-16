package org.example.module19.service;

import lombok.AllArgsConstructor;
import org.example.module19.exception.EntityNotFoundException;
import org.example.module19.mapper.OrderMapper;
import org.example.module19.model.Customer;
import org.example.module19.model.Order;
import org.example.module19.model.dto.request.OrderCreateRequest;
import org.example.module19.model.dto.request.OrderUpdateRequest;
import org.example.module19.model.dto.response.OrderResponse;
import org.example.module19.repository.CustomerRepository;
import org.example.module19.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderResponse getOrderById(Long id) {
        return orderMapper.toOrderResponse(
                orderRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found")));
    }

    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) {
        Order order = orderMapper.toOrder(request);
//        Customer customer = customerRepository.findById(request.customerId()).orElseThrow();
        Customer customer = customerRepository.getReferenceById(request.customerId());
        order.setCustomer(customer);
        orderRepository.save(order);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse updateOrder(Long id, OrderUpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
        order.setStatus(request.status());
        order.setTotalPrice(request.totalPrice());
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

}
