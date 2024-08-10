CREATE TABLE health_logs (
                             log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             pet_id BIGINT,
                             log_date DATE NOT NULL,
                             weight DECIMAL(5,2),
                             appetite VARCHAR(50),
                             mood VARCHAR(50),
                             activity VARCHAR(50),
                             temperature DECIMAL(4,1),
                             symptoms TEXT,
                             notes TEXT,
                             FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
);
