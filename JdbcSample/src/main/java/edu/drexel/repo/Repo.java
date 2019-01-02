package edu.drexel.repo;

import java.util.List;

public interface Repo<T> {
	List<T> getAll(String sql);
	T findById(String sql, int id);
	int insert(String sql, T domain);
	int update(String sql, T domain);
	int delete(String sql, int id);
}
