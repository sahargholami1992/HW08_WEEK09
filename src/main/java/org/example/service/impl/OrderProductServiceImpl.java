package org.example.service.impl;

import org.example.domain.OrderProduct;
import org.example.repository.OrderProductRepository;
import org.example.repository.ProductRepository;
import org.example.service.OrderProductService;

public class OrderProductServiceImpl
implements OrderProductService {

    OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository=orderProductRepository;
    }


    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public void delete(OrderProduct orderProduct) {
        orderProductRepository.delete(orderProduct);
    }

    @Override
    public OrderProduct findByOrderAndProductId(long orderId, long productId) {

        return orderProductRepository.findByOrderAndProductId(orderId, productId);
    }

    @Override
    public void deleteByOrderAndProductId(long orderId, long productId) {
        orderProductRepository.deleteByOrderAndProductId(orderId,productId);
    }

    @Override
    public void update(OrderProduct orderProduct) {
        orderProductRepository.update(orderProduct);
    }
}
