package hu.schonherz.training.service.exam;

import java.util.List;

public interface BaseService<T> {
	
	public T getById(Long id) throws Exception;

	public List<T> getAll(Long...id) throws Exception;
}
