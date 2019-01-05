package edu.drexel.repo;

import java.util.List;

public interface Repo<T> {
	List<T> getAll(String sql, RowMapper<T> rowMapper);
	T findById(String sql, int id, RowMapper<T> rowMapper);
	int insert(String sql, T domain, ParamSetter<T> paramSetter);
	int insertList(String sql, List<T> domain, ParamSetter<T> paramSetter);
	int update(String sql, T domain, ParamSetter<T> paramSetter);
	int delete(String sql, int id);
}
