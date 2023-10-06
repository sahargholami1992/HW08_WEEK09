package org.example.repository;

import org.example.base.repository.BaseEntityRepository;
import org.example.domain.Order;

public interface OrderRepository extends BaseEntityRepository<Order,Long> {
    Order findByCustomerAndPaymentStatus(String paymentStatus, long customerId);
}
