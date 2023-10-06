package org.example.service;

import org.example.base.service.BaseEntityService;
import org.example.domain.Order;

public interface OrderService extends BaseEntityService<Order,Long> {
    Order findByCustomerAndPaymentStatus(String paymentStatus, long customerId);
}
