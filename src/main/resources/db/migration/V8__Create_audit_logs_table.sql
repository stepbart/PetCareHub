CREATE TABLE audit_logs (
                            audit_log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT,
                            action VARCHAR(50),
                            entity VARCHAR(50),
                            entity_id BIGINT,
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(user_id)
);
