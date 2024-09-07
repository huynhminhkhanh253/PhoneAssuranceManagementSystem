CREATE TABLE IF NOT EXISTS `order`(
    id int NOT NULL AUTO_INCREMENT,
    imei varchar(255),
    phone_model varchar(255),
    source varchar(255),
    order_status varchar(255),
    ineligible_reason varchar(255) DEFAULT 'Pending update',
    pending_reason varchar(255) DEFAULT 'Pending update',
    price varchar(255),
    buyer_number varchar(255),
    seller_number varchar(255),
    specs_id varchar(255),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS guarantee_event(
    id int NOT NULL AUTO_INCREMENT,
    date_created timestamp default current_timestamp,
    date_update timestamp default current_timestamp on update current_timestamp,
    imei varchar(255),
    event_type varchar(255),
    purchase_type varchar(255),
    raw_message longtext,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS user(
    id int NOT NULL AUTO_INCREMENT,
    user_name varchar(255),
    password varchar(255),
    UNIQUE(user_name),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS specs(
    id int NOT NULL AUTO_INCREMENT,
    screen_size varchar(255),
    ram varchar(255),
    cpu varchar(255),
    storage varchar(255),
    camera_main varchar(255),
    camera_front varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product(
    id int NOT NULL AUTO_INCREMENT,
    imei varchar(255),
    product_name varchar(255),
    PRIMARY KEY (id)
);