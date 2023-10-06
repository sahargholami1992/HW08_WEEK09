package org.example.repository.impl;

import org.example.base.repository.impl.BaseEntityRepositoryImpl;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl  extends BaseEntityRepositoryImpl<Order,Long>
        implements OrderRepository {


    public OrderRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected List<String> getInsertColumnsForSave() {
        return List.of(new String[]{
                Order.CREATE_AT,
                Order.TOTAL,
                Order.CUSTOMER_ID,
                Order.PAYMENT_STATUS
        });
    }

    @Override
    protected String getEntityTableName() {
        return Order.TABLE_NAME;
    }

    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setId(resultSet.getLong(1));
            order.setCreateAt(resultSet.getString(2));
            order.setTotal(resultSet.getInt(3));
            order.setPaymentStatus(resultSet.getString(4));
            order.setCustomer( (Customer) new CustomerRepositoryImpl(this.connection).findById(resultSet.getLong(5)));
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    protected void fillPreparedStatementParamsForSave(PreparedStatement preparedStatement, Order entity) {
        try {
            preparedStatement.setString(1,entity.getCreateAt());
            preparedStatement.setInt(2,entity.getTotal());
            preparedStatement.setLong(3,entity.getCustomer().getId());
            preparedStatement.setString(4, entity.getPaymentStatus());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void fillPreparedStatementParamsForUpdate(PreparedStatement preparedStatement, Order entity) {
        Order order = new Order();
        try {
            preparedStatement.setString(1,order.getCreateAt());
            preparedStatement.setInt(2,order.getTotal());
            preparedStatement.setLong(3,order.getCustomer().getId());
            preparedStatement.setString(4, entity.getPaymentStatus());
            preparedStatement.setLong(5,order.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Order findByCustomerAndPaymentStatus(String paymentStatus, long customerId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders  " +
                    " WHERE payment_status=? AND customer_id=?");
            preparedStatement.setString(1, paymentStatus);
            preparedStatement.setLong(2, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order;
            if (resultSet.next()) {
                order = mapResultSetToEntity(resultSet);
                return order;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
