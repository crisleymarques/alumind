CREATE TABLE requested_features (
    id BIGINT(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(45) NOT NULL,
    reason VARCHAR(255) NOT NULL,
);
