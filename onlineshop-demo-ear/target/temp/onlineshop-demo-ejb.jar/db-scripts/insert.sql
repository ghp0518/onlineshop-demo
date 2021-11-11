insert into PRODUCT_CATEGORY VALUES( 1, 'DESK', 'Office Desks');
insert into PRODUCT_CATEGORY VALUES( 2, 'SOFA', 'Office Sofas');
insert into PRODUCT_CATEGORY VALUES( 3, 'CHAIRS', 'Office Chairs');
insert into PRODUCT_CATEGORY VALUES( 4, 'CABINETS', 'Office Cabinets');

insert into PRODUCT_INVENTORY VALUES( 1,10,null);
insert into PRODUCT_INVENTORY VALUES( 2,8,null);
insert into PRODUCT_INVENTORY VALUES( 3,7,null);
insert into PRODUCT_INVENTORY VALUES( 4,5,null);
insert into PRODUCT_INVENTORY VALUES(5,10,null);

insert into PRODUCT VALUES(1, 'VINLIDEN', 649, 1, 2, null);
insert into PRODUCT VALUES(2, 'KIVIK',899, 2, 2, null);
insert into PRODUCT VALUES(3, 'BEKANT',299 ,3, 1, null);
insert into PRODUCT VALUES(4, 'ODGER',99, 4, 3, null);
insert into CUSTOMER VALUES(1,'CUSTOMER 1', null, null, null, null);
insert into CUSTOMER VALUES(2,'CUSTOMER 2', null, null, null, null);
insert into CUSTOMER VALUES(3,'CUSTOMER 3', null, null, null, null);

insert into CUSTOMER_DISCOUNT_TOTAL VALUES(1, 'PROMO-C1-T-PC10', 10,1, null);
insert into CUSTOMER_DISCOUNT_TOTAL VALUES(2, 'PROMO-C2-T-PC5', 5, 2, null);

insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(1,'PROMO-C1-PR-2-PC30', 30, 1, 2, null);

insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(2, 'PROMO-C2-PR-3-PC40', 40,2, 3, null);

insert into CUSTOMER_DISCOUNT_PRODUCT_PERCENT  VALUES(3,'PROMO-C3-PR-4-PC35', 35,3, 4, null);

insert into CUSTOMER_DISCOUNT_PRODUCT_QUANTITY  VALUES(1,'PROMO-C1-PR-4-B3-P2', 3,2,1,4,null);