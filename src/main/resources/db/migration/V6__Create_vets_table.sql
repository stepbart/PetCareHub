CREATE TABLE vets (
                      vet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      address VARCHAR(255),
                      phone_number VARCHAR(20),
                      opening_hours VARCHAR(255),
                      services TEXT,
                      specializations TEXT,
                      website VARCHAR(255),
                      ratings DECIMAL(2,1),
                      reviews TEXT
);
