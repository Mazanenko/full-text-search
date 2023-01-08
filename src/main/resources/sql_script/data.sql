INSERT INTO authors(id, created_at, modified_at, first_name, middle_name, last_name)
VALUES (1, now(), now(), 'Эрнест', 'Миллер', 'Хемингуэй'),
       (2, now(), now(), 'Рэй', 'Дуглас', 'Бредбери');

INSERT INTO books(created_at, modified_at, name, genre, description, price, author_id)
VALUES (now(), now(), 'Старик и море', 'Повесть', '', 1500, 1),
       (now(), now(), 'За рекой, в тени деревьев', 'Роман', '', 300, 1),
       (now(), now(), 'Праздник, который всегда с тобой', 'Автобиография', '', 500, 1),
       (now(), now(), 'Марсианские хроники', 'Фантастика', '', 800, 2),
       (now(), now(), '451 градус по Фаренгейту', 'Антиутопия', '', 1500, 2);


