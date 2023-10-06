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
public class Type extends BaseEntity<Long> {
    public static final String TABLE_NAME = "types";

    public static final String NAME = "name";
    private String name;



}
