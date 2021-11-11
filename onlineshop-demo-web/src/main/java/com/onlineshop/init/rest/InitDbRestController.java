package com.onlineshop.init.rest;

import javax.ejb.EJB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.init.InitDataLocal;

@RestController
@RequestMapping(InitDbRestController.BASE_URI)
public class InitDbRestController {
	public static final String BASE_URI = "/api/init";
	
	@EJB(mappedName="java:global/onlineshop-demo-ear/onlineshop-demo-ejb/InitDataEjb")
	InitDataLocal initDataLocal;
	
	@GetMapping(path = "db")
	public void initDb() {
		initDataLocal.deleteTables();
		initDataLocal.createTables();
		initDataLocal.insertData();
	}

}
