use master
create database esunAssignment
GO
use esunAssignment

-- SeatingChart
CREATE TABLE SeatingChart (
    FLOOR_SEAT_SEQ INT PRIMARY KEY,
    FLOOR_NO INT NOT NULL,
    SEAT_NO INT NOT NULL
);

-- Employee
CREATE TABLE Employee (
    EMP_ID CHAR(5) PRIMARY KEY,
    NAME NVARCHAR(25) NOT NULL,
    EMAIL NVARCHAR(30) NOT NULL,
    FLOOR_SEAT_SEQ INT,
    FOREIGN KEY (FLOOR_SEAT_SEQ) REFERENCES SeatingChart(FLOOR_SEAT_SEQ)
);
