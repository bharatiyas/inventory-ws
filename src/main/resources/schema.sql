DROP TABLE IF EXISTS Manufacturer;
DROP TABLE IF EXISTS Inventory;

CREATE TABLE Manufacturer (
      manufacturer_id VARCHAR(100) PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      home_page VARCHAR(45),
      phone_number VARCHAR(12)
);

CREATE TABLE Inventory (
      inventory_id  VARCHAR(100) PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      release_date DATETIME NOT NULL,
      manufacturer_id VARCHAR(100) NOT NULL
);