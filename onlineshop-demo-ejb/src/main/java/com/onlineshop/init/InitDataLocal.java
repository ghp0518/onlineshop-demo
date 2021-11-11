package com.onlineshop.init;

import javax.ejb.Local;

@Local
public interface InitDataLocal {
	
	void deleteTables();
	
	void createTables();
	
	void insertData();

}
