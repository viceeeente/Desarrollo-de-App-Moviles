-- Categorías (sin ID, se generan automáticamente)
INSERT INTO category (name) VALUES ('Consolas');
INSERT INTO category (name) VALUES ('Juegos de mesa');
INSERT INTO category (name) VALUES ('Mouse');
INSERT INTO category (name) VALUES ('Mousepad');
INSERT INTO category (name) VALUES ('Computadores');
INSERT INTO category (name) VALUES ('Poleras');
INSERT INTO category (name) VALUES ('Sillas');

INSERT INTO products (name, description, price, stock, category_id,img) VALUES
('PlayStation 5', 'Consola de última generación de Sony...', 499990, 10, 1,'/img/play_station_5_removebg_preview.png'),
('Xbox Series X', 'La consola más potente de Microsoft...', 449990, 8, 1,'/img/xbox_series_x_removebg_preview.png'),
('Catan', 'Juego de mesa clásico', 29900, 20, 2,'/img/catan.png'),
('Carcassonne', 'Juego de mesa estratégico', 24990, 15, 2,'/img/carcassonne_removebg_preview.png'),
('Mouse Logitech G502 HERO', 'Sensor HERO 25K, 11 botones...', 49990, 30, 3,'/img/mouse_logitech_removebg_preview.png'),
('Mouse Razer DeathAdder V2', 'Sensor óptico 8500 dpi, ergonómico...', 29990, 25, 3,'/img/mouse_razer_removebg_preview.png'),
('Mousepad Razer Goliathus', 'Iluminación RGB, superficie optimizada...', 29990, 40, 4,'/img/mousepad_razer.png'),
('Mousepad Corsair MM300', 'Tela resistente, bordes cosidos...', 19990, 35, 4,'/img/mousepad_corsair_removebg_previwe.png'),
('PC Gamer Ryzen 7 RTX 4060', 'Gaming 2K, multitareas exigentes...', 899990, 5, 5,'/img/pc_amd_removebg_preview.png'),
('Notebook ASUS TUF Gaming', 'Pantalla 144Hz, Ryzen 5, GTX 1650...', 749990, 7, 5,'/img/pc_asus_removebg_preview.png'),
('Polera Level Up', 'Polera gamer', 14990, 50, 6,'/img/polera2_removebg_preview.png'),
('Polera Game On', 'Polera gamer', 14990, 50, 6,'/img/polera1.webp'),
('Silla Secretlab TITAN', 'Comodidad, soporte lumbar, 4D...', 349990, 12, 7,'/img/silla_secret_lab.png'),
('Silla DXRacer Formula', 'Silla gamer clásica', 399990, 10, 7,'/img/silla_dxracer.png');