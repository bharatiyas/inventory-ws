-- Insert records in Manufacturer table
insert into Manufacturer(manufacturer_id,  name, home_page, phone_number) values('f5c1acba-b774-11ea-b3de-0242ac130004','HP','https://www.hp.com','111-222-333');
insert into Manufacturer(manufacturer_id,  name, home_page, phone_number) values('f5c1aec2-b774-11ea-b3de-0242ac130004','Apple','https://www.apple.com Laptop','222-333-444');
insert into Manufacturer(manufacturer_id,  name, home_page, phone_number) values('f5c1afbc-b774-11ea-b3de-0242ac130004','Samsung','https://www.samsung.com','333-444-555');
insert into Manufacturer(manufacturer_id,  name, home_page, phone_number) values('f5c1b098-b774-11ea-b3de-0242ac130004','IBM','https://www.ibm.com','444-555-666');
insert into Manufacturer(manufacturer_id,  name, home_page, phone_number) values('f5c1b2be-b774-11ea-b3de-0242ac130004','Motorola','https://www.motorola.com','555-666-777');

-- Insert records in Inventory table
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c24350-b774-11ea-b3de-0242ac130004', 'HP Monitor','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c24288-b774-11ea-b3de-0242ac130004', 'HP Laptop','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c241b6-b774-11ea-b3de-0242ac130004', 'HP Phone','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23ffe-b774-11ea-b3de-0242ac130004', 'HP Desktop','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23f36-b774-11ea-b3de-0242ac130004', 'HP Printer','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23e78-b774-11ea-b3de-0242ac130004', 'HP Projector','2016-08-29T09:12:33.001Z','f5c1acba-b774-11ea-b3de-0242ac130004');

insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23db0-b774-11ea-b3de-0242ac130004', 'Apple Monitor','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23cde-b774-11ea-b3de-0242ac130004', 'Apple Laptop','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23c0c-b774-11ea-b3de-0242ac130004', 'Apple Phone','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23a40-b774-11ea-b3de-0242ac130004', 'Apple Desktop','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23978-b774-11ea-b3de-0242ac130004', 'Apple Printer','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c238b0-b774-11ea-b3de-0242ac130004', 'Apple Projector','2016-08-29T09:12:33.001Z','f5c1aec2-b774-11ea-b3de-0242ac130004');

insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c237f2-b774-11ea-b3de-0242ac130004', 'Samsung Monitor','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23720-b774-11ea-b3de-0242ac130004', 'Samsung Laptop','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c2364e-b774-11ea-b3de-0242ac130004', 'Samsung Phone','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23572-b774-11ea-b3de-0242ac130004', 'Samsung Desktop','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c2336a-b774-11ea-b3de-0242ac130004', 'Samsung Printer','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c232a2-b774-11ea-b3de-0242ac130004', 'Samsung Projector','2016-08-29T09:12:33.001Z','f5c1afbc-b774-11ea-b3de-0242ac130004');

insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c231da-b774-11ea-b3de-0242ac130004', 'IBM Monitor','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23112-b774-11ea-b3de-0242ac130004', 'IBM Laptop','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c23040-b774-11ea-b3de-0242ac130004', 'IBM Phone','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c22f64-b774-11ea-b3de-0242ac130004', 'IBM Desktop','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c22e7e-b774-11ea-b3de-0242ac130004', 'IBM Printer','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c22cc6-b774-11ea-b3de-0242ac130004', 'IBM Projector','2016-08-29T09:12:33.001Z','f5c1b098-b774-11ea-b3de-0242ac130004');

insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c21556-b774-11ea-b3de-0242ac130004', 'Motorola Monitor','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c21484-b774-11ea-b3de-0242ac130004', 'Motorola Laptop','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c21358-b774-11ea-b3de-0242ac130004', 'Motorola Phone','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c20d86-b774-11ea-b3de-0242ac130004', 'Motorola Desktop','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c20cbe-b774-11ea-b3de-0242ac130004', 'Motorola Printer','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');
insert into Inventory(inventory_id,  name, release_date, manufacturer_id) values('f5c20bec-b774-11ea-b3de-0242ac130004', 'Motorola Projector','2016-08-29T09:12:33.001Z','f5c1b2be-b774-11ea-b3de-0242ac130004');