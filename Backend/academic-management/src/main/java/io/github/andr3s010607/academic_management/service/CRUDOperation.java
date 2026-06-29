package io.github.andr3s010607.academic_management.service;

import java.util.List;

public interface CRUDOperation<T> {
	
	public int create(T data);
	public List<T> getAll();
	public int deleteById(Long id);
	public int UpdateById(Long id, T newData);
	public long count();
	public boolean exist(Long id);
	
}
