package org.example.service.impl;

import org.example.base.service.impl.BaseEntityServiceImpl;
import org.example.domain.Order;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;

public class OrderServiceImpl
        extends BaseEntityServiceImpl<Order,Long, OrderRepository>
        implements OrderService {
    public OrderServiceImpl(OrderRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Order findByCustomerAndPaymentStatus(String paymentStatus, long customerId) {
        return baseRepository.findByCustomerAndPaymentStatus(paymentStatus,customerId);
    }
}
