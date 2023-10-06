package org.example.service.impl;

import org.example.base.service.impl.BaseEntityServiceImpl;
import org.example.domain.Type;
import org.example.repository.TypeRepository;
import org.example.service.TypeService;

public class TypeServiceImpl extends BaseEntityServiceImpl<Type, Long, TypeRepository>
        implements TypeService {
    public TypeServiceImpl(TypeRepository baseRepository) {
        super(baseRepository);
    }
}
