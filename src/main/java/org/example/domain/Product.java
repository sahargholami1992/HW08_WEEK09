package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.domain.BaseEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Long> {
    public static final String NAME = "product_name";
    public static final String PRICE = "price";
    public static final String TABLE_NAME = "products";
    public static final String NUMBER_OF_PRODUCT = "number_product";
    public static final String TYPE = "type_id";
    private String name;
    private Type type;
    private int numbers;
    private double price;

    public Product(long productId) {
        super(productId);
    }
}
