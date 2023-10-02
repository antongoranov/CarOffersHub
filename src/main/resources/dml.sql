-- Some brands
INSERT INTO brands(id, name)
VALUES (1, 'BMW'),
       (2, 'Audi'),
       (3, 'Ford')

INSERT INTO models(id, models.name, category, start_year, end_year, brand_id, image_url)
VALUES
    (1, 'E46', 'CAR', 1999, 2005, 1, 'https://cdn3.focus.bg/autodata/i/bmw/3er/3er-e46/medium/e97806ef0fcb34e9a093dc3021772ede.jpg'),
    (2, 'E90', 'CAR', 2005, 2011, 1, 'https://news-site-za.s3.af-south-1.amazonaws.com/images/2023/01/stock-e90.jpg'),
    (3, 'A4', 'CAR', 2000, null, 2, 'https://a.d-cd.net/791078as-960.jpg'),
    (4, 'Fiesta', 'CAR', 1976, null, 3, 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
    (5, 'Escort', 'CAR', 1968, 2000, 3, 'https://www.auto-data.net/images/f110/Ford-Escort-VII-Hatch-GAL-AFL.jpg');


INSERT INTO users (created,email,first_name,image_url,is_active,last_name,modified,password) VALUES
    (NOW(),'toni03m@abv.bg','Anton',NULL,1,'Goranov',NOW(),'dbc77cbbc78c5cdce6ab1e37c0a7481dde864da5e6db3c739fdf5af9d29a30777a96c716ed47bc69');

INSERT INTO user_roles (user_role)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO users_user_roles (user_entity_id,user_roles_id)
VALUES (1,1);

INSERT INTO offers (id, description, engine, image_url, mileage, price, transmission, year, model_id, seller_id)
VALUES
    ('6b94633d-e932-4c4c-aee0-1e7e18a9c239','Fastest e46 ever! ','DIESEL','https://cdn3.focus.bg/autodata/i/bmw/3er/3er-e46/medium/e97806ef0fcb34e9a093dc3021772ede.jpg',200000,7000.00,'MANUAL',2003,1,1),
    ('ceab385e-08d7-47fd-bfe5-a97377557839','Slower than the e46 ','DIESEL','https://news-site-za.s3.af-south-1.amazonaws.com/images/2023/01/stock-e90.jpg',300000,10000.00,'MANUAL',2005,2,1),
    ('6b94633d-e932-4c4c-aee0-1e7e28a9c234', 'Audi 1', 'GASOLINE', 'https://a.d-cd.net/791078as-960.jpg', 320001, 2601, 'MANUAL', 2005, 3, 2),
    ('6b94633d-e932-4c4c-aee0-1e7e78a9c239', 'Fiesta 1', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg', 320002, 2602, 'MANUAL', 2015, 4, 1),
    ('6b94633d-e932-4c4c-aee0-1e7e68a9c239', 'Fiesta 2.', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg', 320003, 2603, 'MANUAL', 2005, 4, 1),
    ('6b94633d-e932-4c4c-aee0-1e7e28a9c239', 'Fiesta 3', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg', 320004, 2605, 'MANUAL', 2015, 4, 1),
    ('6b94633d-e932-4c4c-aee0-1e7e48a9c239', 'Fiesta 4', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg', 320005, 2604, 'MANUAL', 2005, 4, 1);



