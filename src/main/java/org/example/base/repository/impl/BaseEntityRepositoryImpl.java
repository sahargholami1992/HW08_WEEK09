package org.example.base.repository.impl;

import org.example.base.domain.BaseEntity;
import org.example.base.repository.BaseEntityRepository;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<T extends BaseEntity<ID>,ID extends Serializable>
        implements BaseEntityRepository<T, ID> {

    protected final Connection connection;

    public static final String FIND_BY_ID_QUERY_TEMPLATE = "select * from %s where id = ?";
    public static final String FIND_ALL_QUERY_TEMPLATE = "select * from %s";
    public static final String INSERT_QUERY_TEMPLATE = "insert into %s(%s) values(%s)";
    public static final String UPDATE_QUERY_TEMPLATE = "update %s set %s where id = ?";
    public static final String DELETE_BY_ID_QUERY_TEMPLATE = "delete from %s where id = ?";

    protected BaseEntityRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> findAll(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    String.format(
                            FIND_ALL_QUERY_TEMPLATE,
                            getEntityTableName()
                    )
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet)) ;

            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public T findById(ID id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    String.format(
                            FIND_BY_ID_QUERY_TEMPLATE,
                            getEntityTableName()
                    )
            );
            preparedStatement.setLong(1,(Long) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long count() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select count(*) from " + getEntityTableName()
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public T save(T entity)  {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    generateSave(),
                    Statement.RETURN_GENERATED_KEYS
            );
            fillPreparedStatementParamsForSave(preparedStatement, entity);

            return setGenerateKeyAndReturnEntity(entity, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public T update(T entity) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    generateUpdateQuery()
            );
            fillPreparedStatementParamsForUpdate(preparedStatement, entity);
            preparedStatement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean existsById(ID id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select id from " + getEntityTableName() + " where id = ?"
            );
            preparedStatement.setLong(1,(Long) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void deleteById(ID id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    String.format(
                            DELETE_BY_ID_QUERY_TEMPLATE,
                            getEntityTableName()
                    )
            );
            preparedStatement.setLong(1,(Long) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    protected T setGenerateKeyAndReturnEntity(T entity, PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            generatedKeysResultSet.next();
            entity.setId((ID)Long.valueOf(generatedKeysResultSet.getLong(1)) );
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected String generateSave() {
        List<String> insertColumns = getInsertColumnsForSave();

        return String.format(
                INSERT_QUERY_TEMPLATE,
                getEntityTableName(),
                String.join(",", insertColumns),
                generateQuestionMarkForInsertQuery(insertColumns.size())
        );
    }

    private String generateQuestionMarkForInsertQuery(int length) {
        String questionMarks = "";
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                questionMarks = questionMarks.concat("?");
            } else {
                questionMarks = questionMarks.concat("?, ");
            }
        }
        return questionMarks;
    }

    protected String generateUpdateQuery() {
        List<String> updateColumns = getInsertColumnsForSave();

        String setClause = "";
        for (String updateColumn : updateColumns) {
            setClause = setClause.concat(updateColumn).concat(" = ?,");
        }
        setClause = setClause.substring(0, setClause.length() - 1);
        return String.format(
                UPDATE_QUERY_TEMPLATE,
                getEntityTableName(),
                setClause
        );
    }

    protected abstract List<String> getInsertColumnsForSave();

    protected abstract String getEntityTableName();

    protected abstract T mapResultSetToEntity(ResultSet resultSet) ;

    protected abstract void fillPreparedStatementParamsForSave(PreparedStatement preparedStatement,
                                                               T entity);
    protected abstract void fillPreparedStatementParamsForUpdate(PreparedStatement preparedStatement,
                                                                 T entity);

}
