package hu.schonherz.training.service.exam;

import java.util.List;

public interface BaseService<T> {
	
	public void add(T vo, Long...id) throws Exception;

	public T getById(Long id) throws Exception;

	public List<T> getAll(Long...id) throws Exception;
}
