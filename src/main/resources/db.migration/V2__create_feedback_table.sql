CREATE TABLE feedback (
    id BIGINT(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_requested_features BIGINT(20) UNSIGNED,
    sentiment ENUM('POSITIVO', 'NEGATIVO', 'INCONCLUSIVO') NOT NULL,
    description TEXT,
    CONSTRAINT fk_id_requested_features FOREIGN KEY (id_requested_features) REFERENCES id_requested_features(id)
);
