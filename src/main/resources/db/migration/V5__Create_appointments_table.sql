CREATE TABLE appointments (
                              appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT,
                              appointment_date TIMESTAMP NOT NULL,
                              vet_name VARCHAR(255),
                              address VARCHAR(255),
                              reason VARCHAR(255),
                              notes TEXT,
                              appointment_status VARCHAR(20) DEFAULT 'SCHEDULED',
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(user_id)
);
