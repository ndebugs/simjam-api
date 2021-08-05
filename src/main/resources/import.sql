INSERT INTO member (id, name, birth_date, address) VALUES (1, 'Wawan Setiawan', '1990-01-10', 'kompleks Asia Serasi No 100');
INSERT INTO member (id, name, birth_date, address) VALUES (2, 'Teguh Sudibyantoro', '1991-02-10', 'Jalan Pemekaran No 99');
INSERT INTO member (id, name, birth_date, address) VALUES (3, 'Joko Widodo', '1992-03-10', 'Dusun Pisang Rt 10 Rw 20');

INSERT INTO transaction (id, member_id, type, amount, time) VALUES (1, 1, 'DEPOSIT', 1000000, '2020-08-17');
INSERT INTO transaction (id, member_id, type, amount, time) VALUES (2, 2, 'DEPOSIT', 5000000, '2020-08-18');
INSERT INTO transaction (id, member_id, type, amount, time) VALUES (3, 3, 'WITHDRAW', 2000000, '2020-09-30');
INSERT INTO transaction (id, member_id, type, amount, time) VALUES (4, 3, 'DEPOSIT', 1000000, '2020-11-10');
INSERT INTO transaction (id, member_id, type, amount, time) VALUES (5, 1, 'DEPOSIT', 5000000, '2020-12-01');
INSERT INTO transaction (id, member_id, type, amount, time) VALUES (6, 2, 'WITHDRAW', 2000000, '2020-12-01');
