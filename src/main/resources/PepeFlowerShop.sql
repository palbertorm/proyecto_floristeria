DROP DATABASE IF EXISTS PepeFlowerShop;
CREATE DATABASE PepeFlowerShop;
USE PepeFlowerShop;

CREATE TABLE products (
	id INT NOT NULL PRIMARY KEY,
    type VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    attribute VARCHAR(100) NOT NULL
);

CREATE TABLE sales (
	id INT NOT NULL PRIMARY KEY,
    totalPrice DOUBLE NOT NULL,
    date DATE NOT NULL,
    productList VARCHAR(1000)
);

INSERT INTO products (id, type, name, price, attribute) VALUES
(1,'FLOWER', 'Rosas', 15.5, 'Rojo'),
(2,'TREE', 'Pino', 25.75, '3.5'),
(3,'DECORATION', 'Jarrón', 10.2, 'wood'),
(4,'FLOWER', 'Margaritas', 12.8, 'Blanco'),
(5,'DECORATION', 'Lámpara', 30.0, 'plastic'),
(6,'TREE', 'Roble', 35.25, '4.2'),
(7,'FLOWER', 'Tulipanes', 18.9, 'Amarillo'),
(8,'DECORATION', 'Silla', 22.5, 'wood'),
(9,'FLOWER', 'Lirios', 20.3, 'Púrpura'),
(10,'TREE', 'Arce', 28.6, '3.8'),
(11,'DECORATION', 'Mesa', 45.8, 'wood'),
(12,'FLOWER', 'Girasoles', 14.75, 'Amarillo'),
(13,'DECORATION', 'Estantería', 55.0, 'wood'),
(14,'FLOWER', 'Orquídeas', 30.5, 'Rosado'),
(15,'DECORATION', 'Maceta', 8.9, 'plastic'),
(16,'TREE', 'Abeto', 22.75, '4.0'),
(17,'FLOWER', 'Jacintos', 16.2, 'Azul'),
(18,'DECORATION', 'Sofá', 65.3, 'plastic'),
(19,'TREE', 'Abies', 30.5, '4.1'),
(20,'FLOWER', 'Dalias', 22.8, 'Naranja'),
(21,'TREE', 'Olivo', 53.4, '4.8');

INSERT INTO sales (id, totalPrice, date, productList) VALUES
(1,58.20, '2015-03-10', 'Flower: Rosas, Color: Rojo, Price: 15.50€;Tree: Abeto, Heigth: 4.1, Price: 42.70€'),
(2,97.80, '2016-07-20', 'Decoration: Maceta, Material: PLASTIC, Price: 8.90€;Flower: Orquídeas, Color: Rosado, Price: 22.50€;Tree: Pino, Heigth: 3.5, Price: 66.40€'),
(3,45.30, '2017-11-05', 'Decoration: Sofá, Material: WOOD, Price: 45.30€'),
(4,98.45, '2018-04-15', 'Decoration: Mesa, Material: WOOD, Price: 45.80€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Decoration: Jarrón, Material: WOOD, Price: 12.05€'),
(5,68.70, '2019-09-22', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Decoration: Lámpara, Material: PLASTIC, Price: 29.50€'),
(6,37.80, '2020-05-10', 'Flower: Tulipanes, Color: Amarillo, Price: 18.90€;Decoration: Silla, Material: WOOD, Price: 18.90€'),
(7,79.90, '2015-01-02', 'Decoration: Estantería, Material: PLASTIC, Price: 15.90€;Flower: Margaritas, Color: Blanco, Price: 12.80€;Tree: Abies, Heigth: 4.1, Price: 51.20€'),
(8,125.30, '2016-07-15', 'Decoration: Lámpara, Material: WOOD, Price: 30.00€;Flower: Jacintos, Color: Azul, Price: 16.20€;Tree: Arce, Heigth: 3.8, Price: 78.10€'),
(9,55.40, '2017-11-30', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Flower: Girasoles, Color: Amarillo, Price: 14.75€'),
(10,92.00, '2018-04-20', 'Decoration: Maceta, Material: PLASTIC, Price: 8.90€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Flower: Rosas, Color: Rojo, Price: 42.50€'),
(11,72.30, '2019-08-05', 'Decoration: Estantería, Material: WOOD, Price: 55.00€;Tree: Pino, Heigth: 3.5, Price: 25.75€'),
(12,118.60, '2020-01-30', 'Tree: Abeto, Heigth: 4.1, Price: 78.90€;Flower: Orquídeas, Color: Rosado, Price: 22.50€;Decoration: Maceta, Material: PLASTIC, Price: 17.20€'),
(13,67.80, '2015-02-15', 'Flower: Tulipanes, Color: Amarillo, Price: 18.90€;Tree: Pino, Heigth: 3.5, Price: 25.75€;Decoration: Jarrón, Material: WOOD, Price: 23.15€'),
(14,85.40, '2016-06-10', 'Tree: Abeto, Heigth: 4.1, Price: 63.70€;Flower: Rosas, Color: Rojo, Price: 15.50€;Decoration: Estantería, Material: PLASTIC, Price: 6.20€'),
(15,42.20, '2017-10-25', 'Decoration: Lámpara, Material: WOOD, Price: 30.00€;Flower: Girasoles, Color: Amarillo, Price: 14.75€'),
(16,92.60, '2018-03-05', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Decoration: Mesa, Material: WOOD, Price: 45.80€;Flower: Margaritas, Color: Blanco, Price: 7.60€'),
(17,107.90, '2019-07-20', 'Decoration: Sofá, Material: PLASTIC, Price: 65.30€;Flower: Jacintos, Color: Azul, Price: 16.20€;Tree: Arce, Heigth: 3.8, Price: 26.40€'),
(18,73.80, '2020-12-10', 'Tree: Abies, Heigth: 4.1, Price: 31.50€;Decoration: Maceta, Material: PLASTIC, Price: 15.90€;Flower: Tulipanes, Color: Amarillo, Price: 26.40€'),
(19,69.20, '2015-02-28', 'Flower: Orquídeas, Color: Rosado, Price: 30.50€;Decoration: Maceta, Material: PLASTIC, Price: 19.70€;Tree: Roble, Heigth: 4.2, Price: 18.00€'),
(20,84.30, '2016-06-10', 'Flower: Rosas, Color: Rojo, Price: 15.50€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Decoration: Estantería, Material: WOOD, Price: 28.20€');


