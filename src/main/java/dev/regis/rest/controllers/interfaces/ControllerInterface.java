package dev.regis.rest.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface ControllerInterface<T> {
    public void save(T entity) throws Exception;
	public void delete(@PathVariable Long id);
	
	public T findById(@PathVariable Long id);
	public List<T> listAll();
}
