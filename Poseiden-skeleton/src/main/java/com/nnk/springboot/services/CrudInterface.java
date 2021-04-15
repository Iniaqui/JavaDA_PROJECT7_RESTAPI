package com.nnk.springboot.services;

import java.util.List;

public interface CrudInterface<DataObject> {
	public DataObject save(DataObject data);
	public DataObject update(DataObject data);
	public DataObject readById(int id);
	public DataObject readByName(String name);
	public void deleted(int id);
	public List<DataObject> getAllData();
}
