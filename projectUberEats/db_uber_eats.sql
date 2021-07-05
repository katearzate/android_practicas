 CREATE TABLE users(
                id_user INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, 
                mail TEXT NOT NULL,
                password TEXT NOT NULL,   
                name TEXT NOT NULL,
                telephone TEXT NOT NULL
            );
 
CREATE TABLE categories(
                id_category INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL, 
                category TEXT NOT NULL
);

CREATE TABLE business(
                id_business int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
                business TEXT NOT NULL,
                description TEXT NOT NULL,
                address TEXT NOT NULL,
                latitude double NOT NULL,
                longitude double NOT NULL,
                id_category INT(11) NOT NULL,
                favorite TINYINT(1) NOT NULL DEFAULT '0',
                photo TEXT,
                FOREIGN KEY (id_category) REFERENCES categories(id_category)
);

CREATE TABLE products (
                id_products INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                product TEXT NOT NULL,
                id_business INT(11) NOT NULL,
                price double NOT NULL,
                photo TEXT,
                product_description TEXT,
                FOREIGN KEY (id_business) REFERENCES business(id_business)
);

CREATE TABLE favorites (
                id_favorite INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
                id_user INT(11) NOT NULL,
                id_business INT(11) NOT NULL,
                FOREIGN KEY (id_user) REFERENCES users(id_user),
                FOREIGN KEY (id_business) REFERENCES business(id_business)
);


INSERT INTO users VALUES(1, 'ash@itm.edu.mx', SHA1('123'), 'Ashley Roberta', '4433001122');

INSERT INTO categories VALUES 
(1,'Mexicana'),
(2,'Japonesa'),
(3,'Italiana'),
(4,'Rapida'),
(5,'Cafeteria'),
(6,'Otros');

INSERT INTO business VALUES 
(1,'Pizzas Mario Bros','Auntenticas pizzas italianas','Av Tec 1500 Col Lomas de Santiaguito',19.7229386,-101.1858201,4,0,'pizza.png'),
(2,'Cafe y Donas Homero','Comienza bien tu día con nosotros','Av Tec 1500 Col Lomas de Santiaguito',19.7229386,-101.1858201,5,0,'donas.jpg');

INSERT INTO products VALUES 
(1,'Pizza de peperoni',1,120.5,'pizza.png','Queso mozarella, peperoni, champiñones y albahca'),
(2,'Pizza especial de la casa',1,120.5,'pizza.png','Queso mozarella, albondigas, romero, champiñones y albahaca');
