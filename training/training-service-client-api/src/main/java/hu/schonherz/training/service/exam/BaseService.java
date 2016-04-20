package hu.schonherz.training.service.exam;

import java.util.List;

public interface BaseService<T> {
	
	public void create(T vo) throws Exception;

	public T findById(Long id) throws Exception;

	public List<T> findAll() throws Exception;

	

}
