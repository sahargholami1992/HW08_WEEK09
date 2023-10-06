package org.example.repository.impl;

import org.example.base.repository.impl.BaseEntityRepositoryImpl;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl extends BaseEntityRepositoryImpl<Customer,Long>
        implements CustomerRepository {
    public CustomerRepositoryImpl(Connection connection) {
        super(connection);
    }
    public Customer login(String userName) {

        try {
            String loginQuery = "SELECT * FROM customers WHERE user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("customer_name"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("address")
                );
                return customer;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean existByUsernameAndPassword(String username, String password) {
        try {
            String sql = "SELECT * FROM customers WHERE user_name = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer findByUsername(String username) {
        try {
            String sql = "SELECT * FROM customers WHERE user_name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = new Customer();
            if (resultSet.next()) {
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setUserName(resultSet.getString("user_name"));
                customer.setPassword(resultSet.getString("password"));
                customer.setAddress(resultSet.getString("address"));
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected List<String> getInsertColumnsForSave() {
        List<String> columns = new ArrayList<>();
        columns.add(Customer.NAME);
        columns.add(Customer.USER_NAME);
        columns.add(Customer.PASSWORD);
        columns.add(Customer.ADDRESS);
        return columns;
    }

    @Override
    protected String getEntityTableName() {
        return Customer.TABLE_NAME;
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet resultSet) {
        try {
            Customer customer = new Customer();
            customer.setId(resultSet.getLong(1));
            customer.setName(resultSet.getString(2));
            customer.setUserName(resultSet.getString(3));
            customer.setPassword(resultSet.getString(4));
            customer.setAddress(resultSet.getString(5));
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    protected void fillPreparedStatementParamsForSave(PreparedStatement preparedStatement, Customer entity) {

        try {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getUserName());
            preparedStatement.setString(3,entity.getPassword());
            preparedStatement.setString(4,entity.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void fillPreparedStatementParamsForUpdate(PreparedStatement preparedStatement, Customer entity) {

        try {
            Customer customer = new Customer();
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getUserName());
            preparedStatement.setString(3,customer.getPassword());
            preparedStatement.setString(4,customer.getAddress());
            preparedStatement.setLong(5,customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
