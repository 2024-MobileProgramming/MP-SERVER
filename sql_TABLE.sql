SHOW DATABASES;
USE MP;
SHOW tables;

CREATE TABLE User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(200) NOT NULL,
    user_nickname VARCHAR(250) NOT NULL,
    user_profile LONGBLOB,
    user_kakao_email VARCHAR(255) UNIQUE,
    user_birthday VARCHAR(10),
    user_birthyear VARCHAR(4),
    user_phone VARCHAR(20) UNIQUE,
    CONSTRAINT unique_user_info UNIQUE (user_name, user_nickname, user_kakao_email, user_phone)
);


CREATE TABLE Mission (
    mission_id INT PRIMARY KEY AUTO_INCREMENT,
    mission_name VARCHAR(255),
    mission_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Part_miss (
    user_id INT,
    mission_id INT,
    mission_name VARCHAR(255),
    mission_photo LONGBLOB,
    checked_count INT DEFAULT 0,
    PRIMARY KEY (user_id, mission_id),
    FOREIGN KEY (user_id)
        REFERENCES User(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (mission_id)
        REFERENCES Mission(mission_id)
        ON DELETE CASCADE
);


CREATE TABLE UserCheck (
    check_id INT PRIMARY KEY AUTO_INCREMENT,
    host_id INT,
    user_id INT,
    mission_id INT,
    checked BOOLEAN,
    FOREIGN KEY (host_id)
        REFERENCES User(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES User(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (mission_id)
        REFERENCES Mission(mission_id)
        ON DELETE CASCADE
);

SHOW TABLES;

SELECT * FROM User;
SELECT * FROM Mission;
SELECT * FROM Part_miss;
SELECT * FROM UserCheck;
