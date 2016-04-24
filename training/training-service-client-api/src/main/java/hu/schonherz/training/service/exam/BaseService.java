package hu.schonherz.training.service.exam;

import java.util.List;

public interface BaseService<T> {
	
	public List<T> getAll() throws Exception;

	public T getById(Long id) throws Exception;

	public void removeById(Long id) throws Exception;
}
