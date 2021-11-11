package com.onlineshop.init;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InitDataEjb  implements InitDataLocal{
	@PersistenceContext(unitName = "onlineShopDB")
    private EntityManager em;

	@Override
	public void createTables() {
		//TODO should improve this
		Query q = em.createNativeQuery("CREATE TABLE CUSTOMER(ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255), PHONE VARCHAR(255), EMAIL VARCHAR(255), ADDRESS VARCHAR(255), DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE PRODUCT_CATEGORY(ID INT NOT NULL, CATEGORY_CODE VARCHAR(64), DESCRIPTION VARCHAR(255));\r\n"
				+ "\r\n"
				+ "CREATE TABLE PRODUCT_INVENTORY(ID INT NOT NULL, QUANTITY INT NOT NULL, DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE PRODUCT(ID INT NOT NULL PRIMARY KEY, NAME VARCHAR(255), PRICE DECIMAL(15,2) NOT NULL, PRODUCT_INVENTORY_ID INT REFERENCES PRODUCT_INVENTORY(ID) , PRODUCT_CATEGORY_ID INT REFERENCES PRODUCT_CATEGORY(ID), DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE CUSTOMER_DISCOUNT_TOTAL(ID INT NOT NULL PRIMARY KEY, DISCOUNT_CODE VARCHAR(128), DISCOUNT_PERCENT INT NOT NULL, CUSTOMER_ID INT REFERENCES CUSTOMER(ID) , DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE CUSTOMER_DISCOUNT_PRODUCT_PERCENT(ID INT NOT NULL PRIMARY KEY, DISCOUNT_CODE VARCHAR(128), DISCOUNT_PERCENT INT NOT NULL, CUSTOMER_ID INT REFERENCES CUSTOMER(ID), PRODUCT_ID INT REFERENCES PRODUCT(ID), DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE CUSTOMER_DISCOUNT_PRODUCT_QUANTITY(ID INT NOT NULL PRIMARY KEY, DISCOUNT_CODE VARCHAR(128), BUY_QUANTITY INT NOT NULL, PAY_QUANTITY INT NOT NULL, CUSTOMER_ID INT REFERENCES CUSTOMER(ID), PRODUCT_ID INT REFERENCES PRODUCT(ID), DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE ORDERS (ID INT PRIMARY KEY NOT NULL, CREATED_TS DATE, CUSTOMER_ID INT REFERENCES CUSTOMER(ID), TOTAL_PRICE DECIMAL(15,2) NOT NULL,  DISCOUNT DECIMAL(15,2)  , DISCOUNT_CODE VARCHAR(128) , DELETED_TS DATE);\r\n"
				+ "\r\n"
				+ "CREATE TABLE ORDER_ITEMS (ID INT PRIMARY KEY NOT NULL, ORDER_ID INT  REFERENCES ORDERS(ID), PRODUCT_ID INT REFERENCES PRODUCT(ID), QUANTITY INT,  PRICE DECIMAL(15,2) NOT NULL);");
		q.executeUpdate();
	}

	@Override
	public void insertData() {
		//TODO should improve this
		Query q = em.createNativeQuery("insert into PRODUCT_CATEGORY VALUES( 1, 'DESK', 'Office Desks');\r\n"
				+ "insert into PRODUCT_CATEGORY VALUES( 2, 'SOFA', 'Office Sofas');\r\n"
				+ "insert into PRODUCT_CATEGORY VALUES( 3, 'CHAIRS', 'Office Chairs');\r\n"
				+ "insert into PRODUCT_CATEGORY VALUES( 4, 'CABINETS', 'Office Cabinets');\r\n"
				+ "\r\n"
				+ "insert into PRODUCT_INVENTORY VALUES( 1,10,null);\r\n"
				+ "insert into PRODUCT_INVENTORY VALUES( 2,8,null);\r\n"
				+ "insert into PRODUCT_INVENTORY VALUES( 3,7,null);\r\n"
				+ "insert into PRODUCT_INVENTORY VALUES( 4,5,null);\r\n"
				+ "insert into PRODUCT_INVENTORY VALUES(5,10,null);\r\n"
				+ "\r\n"
				+ "insert into PRODUCT VALUES(1, 'VINLIDEN', 649, 1, 2, null);\r\n"
				+ "insert into PRODUCT VALUES(2, 'KIVIK',899, 2, 2, null);\r\n"
				+ "insert into PRODUCT VALUES(3, 'BEKANT',299 ,3, 1, null);\r\n"
				+ "insert into PRODUCT VALUES(4, 'ODGER',99, 4, 3, null);\r\n"
				+ "insert into CUSTOMER VALUES(1,'CUSTOMER 1', null, null, null, null);\r\n"
				+ "insert into CUSTOMER VALUES(2,'CUSTOMER 2', null, null, null, null);\r\n"
				+ "insert into CUSTOMER VALUES(3,'CUSTOMER 3', null, null, null, null);\r\n"
				+ "\r\n"
				+ "insert into CUSTOMER_DISCOUNT_TOTAL VALUES(1, 'PROMO-C1-T-PC10', 10,1, null);\r\n"
				+ "insert into CUSTOMER_DISCOUNT_TOTAL VALUES(2, 'PROMO-C2-T-PC5', 5, 2, null);\r\n"
				+ "\r\n"
				+ "insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(1,'PROMO-C1-PR-2-PC30', 30, 1, 2, null);\r\n"
				+ "\r\n"
				+ "insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(2, 'PROMO-C2-PR-3-PC40', 40,2, 3, null);\r\n"
				+ "\r\n"
				+ "insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(3,'PROMO-C3-PR-4-PC35', 35,3, 4, null);\r\n"
				+ "\r\n"
				+ "insert into CUSTOMER_DISCOUNT_PRODUCT_QUANTITY  VALUES(1,'PROMO-C1-PR-4-B3-P2', 3,2,1,4,null);");
		q.executeUpdate();
		
	}

	@Override
	public void deleteTables() {
		//TODO should improve this
		Query q = em.createNativeQuery("DROP TABLE IF EXISTS CUSTOMER_DISCOUNT_TOTAL;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS CUSTOMER_DISCOUNT_PRODUCT_PERCENT;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS CUSTOMER_DISCOUNT_PRODUCT_QUANTITY;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS ORDER_ITEMS ;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS ORDERS;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS PRODUCT;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS PRODUCT_INVENTORY;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS PRODUCT_CATEGORY;\r\n"
				+ "\r\n"
				+ "DROP TABLE IF EXISTS CUSTOMER;");
		q.executeUpdate();
		
	}

}
