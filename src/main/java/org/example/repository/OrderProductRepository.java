package org.example.repository;

import org.example.base.repository.BaseEntityRepository;
import org.example.domain.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public interface OrderProductRepository {
    void save(OrderProduct orderProduct);
    void delete(OrderProduct orderProduct);
    OrderProduct findByOrderAndProductId(long orderId, long productId);

    void deleteByOrderAndProductId(long orderId, long productId);
    void update(OrderProduct orderProduct);


}
