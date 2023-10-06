package org.example.repository.impl;

import org.example.base.repository.impl.BaseEntityRepositoryImpl;
import org.example.domain.Type;
import org.example.repository.TypeRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepositoryImpl extends BaseEntityRepositoryImpl<Type, Long>
        implements TypeRepository {
    public TypeRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected List<String> getInsertColumnsForSave() {
        List<String> columns = new ArrayList<>();
        columns.add(Type.NAME);
        return columns;
    }



    @Override
    protected String getEntityTableName() {
        return Type.TABLE_NAME;

    }

    @Override
    protected Type mapResultSetToEntity(ResultSet resultSet) {
        try {
            Type type = new Type();
            type.setId(
                    resultSet.getLong(1)
            );
            type.setName(
                    resultSet.getString(2)
            );
            return type;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void fillPreparedStatementParamsForSave(PreparedStatement preparedStatement, Type entity) {
        try {
            preparedStatement.setString(
                    1,
                    entity.getName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void fillPreparedStatementParamsForUpdate(PreparedStatement preparedStatement, Type entity) {
        try {
            preparedStatement.setString(
                    1,
                    entity.getName()
            );
            preparedStatement.setLong(
                    2,
                    entity.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
