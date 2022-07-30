package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlProcessor<T> {
    T executeQuery(PreparedStatement st) throws SQLException;
}
