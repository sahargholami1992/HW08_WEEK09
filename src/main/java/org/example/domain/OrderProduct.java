package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct{
    public static final String TABLE_NAME = "order_product";
    public static final String ORDER_ID = "order_id";
    public static final String PRODUCT_ID = "product_id";
    public static final String QUANTITY = "quantity";


    private Order order;
    private Product product;
    private int quantity;


}
