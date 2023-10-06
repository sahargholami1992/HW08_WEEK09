package org.example.repository.impl;

import org.example.base.repository.impl.BaseEntityRepositoryImpl;
import org.example.domain.Customer;
import org.example.domain.Product;
import org.example.domain.Type;
import org.example.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl extends BaseEntityRepositoryImpl<Product,Long>
        implements ProductRepository {
    public ProductRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected List<String> getInsertColumnsForSave() {
        return List.of(new String[]{
                Product.NAME,
                Product.NUMBER_OF_PRODUCT,
                Product.PRICE,
                Product.TYPE,
        });
    }

    @Override
    protected String getEntityTableName() {
        return Product.TABLE_NAME;
    }

    @Override
    protected Product mapResultSetToEntity(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setId(resultSet.getLong(1));
            product.setName(resultSet.getString(2));
            product.setNumbers(resultSet.getInt(3));
            product.setPrice(resultSet.getDouble(4));
            product.setType((Type) new TypeRepositoryImpl(this.connection).findById(resultSet.getLong(5)));
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    protected void fillPreparedStatementParamsForSave(PreparedStatement preparedStatement, Product entity) {
        try {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getNumbers());
            preparedStatement.setDouble(3,entity.getPrice());
            preparedStatement.setLong(4,entity.getType().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void fillPreparedStatementParamsForUpdate(PreparedStatement preparedStatement, Product entity) {
        try {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getNumbers());
            preparedStatement.setDouble(3,entity.getPrice());
            preparedStatement.setLong(4,entity.getType().getId());
            preparedStatement.setLong(5,entity.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> findByType(int typeId) {
        try {
            List<Product> products = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE type_id=?");
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = mapResultSetToEntity(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    }

