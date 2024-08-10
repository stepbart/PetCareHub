CREATE TABLE pets (
                      pet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT,
                      name VARCHAR(100) NOT NULL,
                      species VARCHAR(50),
                      breed VARCHAR(50),
                      age INT,
                      sex VARCHAR(10),
                      weight DECIMAL(5,2),
                      microchip_number VARCHAR(50),
                      adopted BOOLEAN DEFAULT FALSE,
                      adopted_date DATE,
                      FOREIGN KEY (user_id) REFERENCES users(user_id)
);
