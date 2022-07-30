package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void executeQuery(String request) {
        executeQuery(request, PreparedStatement::execute);
    }

    public <T> T executeQuery(String request, SqlProcessor<T> sqlProcessor) {
        try (Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(request)) {
            return sqlProcessor.executeQuery(ps);
        } catch (PSQLException e) {
            throw new ExistStorageException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
