DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    tanggal_lahir DATE NOT NULL,
    no_telf INT(15) NOT NULL,
    pin INT(6) NOT NULL,
    jumlah_saldo DOUBLE(15, 2) NOT NULL
);