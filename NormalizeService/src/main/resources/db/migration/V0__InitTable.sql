CREATE TABLE IF NOT EXISTS online_message(
    id int NOT NULL AUTO_INCREMENT,
    imei varchar(255),
    UNIQUE(imei),
    raw_message LONGTEXT,
    date_created timestamp default current_timestamp,
    date_update timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS partner_message(
    id int NOT NULL AUTO_INCREMENT,
    imei varchar(255),
    UNIQUE(imei),
    raw_message LONGTEXT,
    date_created timestamp default current_timestamp,
    date_update timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS physical_message(
    id int NOT NULL AUTO_INCREMENT,
    imei varchar(255),
    UNIQUE(imei),
    raw_message LONGTEXT,
    date_created timestamp default current_timestamp,
    date_update timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY (id)
);
