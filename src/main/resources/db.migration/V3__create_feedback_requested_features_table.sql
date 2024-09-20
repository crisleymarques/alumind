CREATE TABLE feedback_requested_features (
     id_feedback BIGINT NOT NULL,
     id_requested_feature BIGINT NOT NULL,
     PRIMARY KEY (id_feedback, id_requested_feature),
     FOREIGN KEY (id_feedback) REFERENCES feedback(id),
     FOREIGN KEY (id_requested_feature) REFERENCES requested_features(id)
);
