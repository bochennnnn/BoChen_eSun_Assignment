-- SeatingChart

INSERT INTO SeatingChart (FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO) VALUES 
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 1),
(6, 2, 2),
(7, 2, 3),
(8, 2, 4),
(9, 3, 1),
(10, 3, 2),
(11, 3, 3),
(12, 3, 4),
(13, 4, 1),
(14, 4, 2),
(15, 4, 3),
(16, 4, 4);

-- Employee

INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES 
('12345', 'Alice', 'alice@example.com', NULL),
('67890', 'Bob', 'bob@example.com', NULL),
('13579', 'Charlie', 'charlie@example.com', NULL),
('24680', 'David', 'david@example.com', NULL),
('11223', 'Eve', 'eve@example.com', NULL),
('33445', 'Frank', 'frank@example.com', NULL),
('55667', 'Grace', 'grace@example.com', NULL),
('77889', 'Hank', 'hank@example.com', NULL),
('99000', 'Ivy', 'ivy@example.com', NULL),
('10101', 'Jack', 'jack@example.com', 10);