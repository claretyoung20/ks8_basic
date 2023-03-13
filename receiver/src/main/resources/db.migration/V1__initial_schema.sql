CREATE TABLE message
(
    id            INT AUTO_INCREMENT NOT NULL,
    sender_name   VARCHAR(255)       NOT NULL,
    receiver_name VARCHAR(255)       NOT NULL,
    date_created  datetime           NOT NULL,
    date_updated  datetime           NOT NULL,
    amount        DECIMAL            NOT NULL,
    charges       DECIMAL            NOT NULL,
    ref           VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_sender PRIMARY KEY (id)
);
