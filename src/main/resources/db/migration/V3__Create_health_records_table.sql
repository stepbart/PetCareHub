CREATE TABLE health_records (
                                record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                pet_id BIGINT,
                                vet_id BIGINT,
                                record_date DATE NOT NULL,
                                notes TEXT,
                                vaccine_name VARCHAR(255),
                                medication VARCHAR(255),
                                next_visit_date DATE,
                                FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
);
