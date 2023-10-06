package org.example.repository.impl;

import org.example.domain.ReceiptSheet;
import org.example.repository.ReceiptSheetRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptSheetRepositoryImpl implements ReceiptSheetRepository {
    protected final Connection connection;

    public ReceiptSheetRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ReceiptSheet> showReceiptSheet(long customerId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT p.product_name , p.price, op.quantity\n" +
                    "FROM orders o\n" +
                    "    INNER JOIN order_product op ON o.id=op.order_id\n" +
                    "    INNER JOIN products p on op.product_id = p.id\n" +
                    "WHERE o.payment_status = 'not_payed' AND o.customer_id=?;");
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ReceiptSheet> receiptSheetList = new ArrayList<>();
            while (resultSet.next()) {
                    ReceiptSheet receiptSheet = new ReceiptSheet();
                receiptSheet.setName(resultSet.getString("product_name"));
                receiptSheet.setPrice(resultSet.getDouble("price"));
                receiptSheet.setQuantity(resultSet.getInt("quantity"));
                receiptSheetList.add(receiptSheet);
            }
            return receiptSheetList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    }

