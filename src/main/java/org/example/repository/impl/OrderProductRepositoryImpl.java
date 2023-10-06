package org.example.repository.impl;
import org.example.domain.OrderProduct;
import org.example.domain.Product;
import org.example.domain.Order;
import org.example.repository.OrderProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderProductRepositoryImpl
        implements OrderProductRepository {
    Connection connection;

    public OrderProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(OrderProduct orderProduct) {
        try {
            String sql = "insert into order_product(order_id,product_id,quantity) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,orderProduct.getOrder().getId());
            preparedStatement.setLong(2,orderProduct.getProduct().getId());
            preparedStatement.setInt(3,orderProduct.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(OrderProduct orderProduct) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Delete from order_product where order_id=? AND product_id=? "
            );
            preparedStatement.setLong(1,orderProduct.getOrder().getId());
            preparedStatement.setLong(2,orderProduct.getProduct().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public OrderProduct findByOrderAndProductId(long orderId, long productId) {
        try {
            String sqlQuery = "SELECT * FROM order_product WHERE order_id = ? AND product_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderProduct orderProduct;
            if (resultSet.next()) {
                orderProduct = new OrderProduct();
                orderProduct.setOrder(new Order(resultSet.getLong("order_id")));
                orderProduct.setProduct(new Product(resultSet.getLong("product_id")));
                orderProduct.setQuantity(resultSet.getInt("quantity"));
                return orderProduct;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteByOrderAndProductId(long orderId, long productId) {
        try {
            String sqlQuery = "Delete FROM order_product WHERE order_id = ? AND product_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrderProduct orderProduct) {
        String sqlQuery = "update order_product set quantity=? WHERE order_id = ? and product_id=? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,orderProduct.getQuantity());
            preparedStatement.setLong(2, orderProduct.getOrder().getId());
            preparedStatement.setLong(3, orderProduct.getProduct().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

