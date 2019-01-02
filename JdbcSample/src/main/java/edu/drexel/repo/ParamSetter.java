package edu.drexel.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParamSetter<T> {
	void setParams(T domain, PreparedStatement pstmt) throws SQLException;
}
