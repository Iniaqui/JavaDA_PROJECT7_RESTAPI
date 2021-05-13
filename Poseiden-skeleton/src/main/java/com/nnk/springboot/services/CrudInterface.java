package com.nnk.springboot.services;

import java.util.List;

/**
 * Interface to generate CRUD methods 
 * @author maure
 *
 * @param <DataObject>
 */
public interface CrudInterface<DataObject> {
	public DataObject save(DataObject data);
	public DataObject update(DataObject data);
	public DataObject readById(int id);
	public void deleted(int id);
	public List<DataObject> getAllData();
}
