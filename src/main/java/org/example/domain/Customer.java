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
public class Customer extends BaseEntity<Long> {
    public static final String TABLE_NAME = "customers";
    public static final String NAME = "customer_name";
    public static final String ADDRESS = "address";
    public static final String USER_NAME = "user_name" ;
    public static final String PASSWORD = "password";
    String name;
    String userName;
    String password;
    String address;



//    public Customer(String name, String userName, String password, String address) {
//        this.name=name;
//        this.userName=userName;
//        this.password=password;
//        this.address=address;
//    }


}
