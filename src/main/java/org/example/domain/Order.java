package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.domain.BaseEntity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity<Long> {

    public static final String TABLE_NAME = "orders";
    public static final String CREATE_AT = "create_at";
    public static final String TOTAL = "total";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String PAYMENT_STATUS = "payment_status";
    private String createAt;
    private int total;
    private Customer customer;
    private String paymentStatus;

    public Order(long orderId) {
        super(orderId);
    }

    public Order(Customer customer, String createAt, String paymentStatus) {
        this.customer=customer;
        this.createAt=createAt;
        this.paymentStatus=paymentStatus;
    }
}
