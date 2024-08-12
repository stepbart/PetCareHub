DROP TABLE IF EXISTS appointments CASCADE;
DROP TABLE IF EXISTS archived_users CASCADE;
DROP TABLE IF EXISTS audit_logs CASCADE;
DROP TABLE IF EXISTS health_logs CASCADE;
DROP TABLE IF EXISTS health_records CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS pets CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS vets CASCADE;

CREATE TABLE users (
                       user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       organization_name VARCHAR(255),
                       address VARCHAR(255),
                       phone_number VARCHAR(20),
                       status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       version INT
);

CREATE TABLE pets (
                      pet_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      user_id UUID,
                      name VARCHAR(100) NOT NULL,
                      species VARCHAR(50),
                      breed VARCHAR(50),
                      age INT,
                      sex VARCHAR(10),
                      weight DECIMAL(5,2),
                      microchip_number VARCHAR(50) UNIQUE,
                      adopted BOOLEAN DEFAULT FALSE,
                      adopted_date DATE,
                      FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE health_records (
                                record_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                pet_id UUID,
                                vet_id UUID,
                                record_date DATE NOT NULL,
                                notes TEXT,
                                vaccine_name VARCHAR(255),
                                medication VARCHAR(255),
                                next_visit_date DATE,
                                FOREIGN KEY (pet_id) REFERENCES pets(pet_id) ON DELETE CASCADE
);

CREATE TABLE health_logs (
                             log_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             pet_id UUID,
                             log_date DATE NOT NULL,
                             weight DECIMAL(5,2),
                             appetite VARCHAR(50),
                             mood VARCHAR(50),
                             activity VARCHAR(50),
                             temperature DECIMAL(4,1),
                             symptoms TEXT,
                             notes TEXT,
                             FOREIGN KEY (pet_id) REFERENCES pets(pet_id) ON DELETE CASCADE
);

CREATE TABLE appointments (
                              appointment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              user_id UUID,
                              appointment_date TIMESTAMP NOT NULL,
                              vet_name VARCHAR(255),
                              address VARCHAR(255),
                              reason VARCHAR(255),
                              notes TEXT,
                              appointment_status VARCHAR(20) DEFAULT 'SCHEDULED',
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE vets (
                      vet_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
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

CREATE TABLE notifications (
                               notification_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               user_id UUID,
                               message TEXT NOT NULL,
                               status VARCHAR(20) DEFAULT 'UNREAD',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE audit_logs (
                            audit_log_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                            user_id UUID,
                            action VARCHAR(50),
                            entity VARCHAR(50),
                            entity_id UUID,
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE archived_users (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                email VARCHAR(255) NOT NULL,
                                password VARCHAR(255) NOT NULL,
                                role VARCHAR(50) NOT NULL,
                                first_name VARCHAR(100),
                                last_name VARCHAR(100),
                                organization_name VARCHAR(255),
                                address VARCHAR(255),
                                phone_number VARCHAR(20),
                                created_at TIMESTAMP,
                                updated_at TIMESTAMP,
                                deleted_at TIMESTAMP
);
