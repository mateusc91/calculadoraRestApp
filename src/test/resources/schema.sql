-- Remova a tabela se ela já existir
DROP TABLE IF EXISTS Expressao;

CREATE TABLE Expressao (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           expressao VARCHAR(255),
                           resultado DECIMAL(5)
);