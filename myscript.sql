-- To generate database
-- FRESHER
DROP TABLE IF EXISTS fresher;
CREATE TABLE fresher
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    deleted       BIT(1)       NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    name          VARCHAR(255) NOT NULL,
    dob           date         NOT NULL,
    address       VARCHAR(255) NULL,
    phone         VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    CONSTRAINT pk_fresher PRIMARY KEY (id)
);


-- CENTER
DROP TABLE IF EXISTS center;
CREATE TABLE center
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)       NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    name          VARCHAR(255) NOT NULL,
    code          VARCHAR(255) NULL,
    dob           date         NOT NULL,
    address       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_center PRIMARY KEY (id)
);



-- CENTER FRESHER
DROP TABLE IF EXISTS center_fresher;
CREATE TABLE center_fresher
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1) NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    center_id     BIGINT NULL,
    fresher_id    BIGINT NULL,
    start_date    date NULL,
    end_date      date NULL,
    CONSTRAINT pk_center_fresher PRIMARY KEY (id)
);

ALTER TABLE center_fresher
    ADD CONSTRAINT FK_CENTER_FRESHER_ON_CENTER FOREIGN KEY (center_id) REFERENCES center (id);

ALTER TABLE center_fresher
    ADD CONSTRAINT FK_CENTER_FRESHER_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);


-- programming_language
DROP TABLE IF EXISTS programming_language;
CREATE TABLE programming_language
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_programming_language PRIMARY KEY (id)
);
-- fresher_language
DROP TABLE IF EXISTS fresher_language;
CREATE TABLE fresher_language
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    language_id   BIGINT                NOT NULL,
    fresher_id    BIGINT                NOT NULL,
    CONSTRAINT pk_fresher_language PRIMARY KEY (id)
);

ALTER TABLE fresher_language
    ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);

ALTER TABLE fresher_language
    ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES programming_language (id);


-- assignment
DROP TABLE IF EXISTS assignment;
CREATE TABLE assignment
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    percentage    INT                   NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_assignment PRIMARY KEY (id)
);
-- assignment_score
DROP TABLE IF EXISTS assignment_score;
CREATE TABLE assignment_score
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    fresher_id    BIGINT                NOT NULL,
    assignment_id BIGINT                NOT NULL,
    score         INT                   NOT NULL,
    CONSTRAINT pk_assignment_score PRIMARY KEY (id)
);

ALTER TABLE assignment_score
    ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_ASSIGNMENT FOREIGN KEY (assignment_id) REFERENCES assignment (id);

ALTER TABLE assignment_score
    ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);

INSERT INTO fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUES
(1,0,NOW(),NULL,'Phung','2000-12-11','Ha Noi','011111111','Phung@gmail.com');
INSERT INTO fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUES
(2,0,NOW(),NULL,'Hung','1997-10-15','Ha Noi','0222222222','Hung@gmail.com');
INSERT INTO fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUES
(3,0,NOW(),NULL,'Nhi','2000-05-03','Ha Noi','0333333333','Nhi@gmail.com');


INSERT INTO center(id, deleted, created_on, last_modified, name, code, dob, address) VALUES
(1,0,NOW(),NULL,'Center 5','C5','2021-10-05','Floor 10');
INSERT INTO center(id, deleted, created_on, last_modified, name, code, dob, address) VALUES
(2,0,NOW(),NULL,'Center 18','C18','2021-05-18','Floor 5');
INSERT INTO center(id, deleted, created_on, last_modified, name, code, dob, address) VALUES
(3,0,NOW(),NULL,'Center 10','C10','2021-10-10','Floor 8');


INSERT INTO center_fresher(id, deleted, created_on, last_modified, center_id, fresher_id, start_date, end_date) VALUES
(1,0,NOW(),NULL,1,1,'2022-09-15',NULL);
INSERT INTO center_fresher(id, deleted, created_on, last_modified, center_id, fresher_id, start_date, end_date) VALUES
(2,0,NOW(),NULL,2,2,'2022-10-15',NULL);
INSERT INTO center_fresher(id, deleted, created_on, last_modified, center_id, fresher_id, start_date, end_date) VALUES
(3,0,NOW(),NULL,3,3,'2022-08-15',NULL);


INSERT INTO programming_language(id, deleted, created_on, last_modified, name, description) VALUES
(1,0,NOW(),NULL,'Java','Java language');
INSERT INTO programming_language(id, deleted, created_on, last_modified, name, description) VALUES
(2,0,NOW(),NULL,'C#','C# language');
INSERT INTO programming_language(id, deleted, created_on, last_modified, name, description) VALUES
(3,0,NOW(),NULL,'Python','Python language');


INSERT INTO fresher_language(id, deleted, created_on, last_modified, language_id, fresher_id) VALUES
(1,0,NOW(),NULL,1,1);
INSERT INTO fresher_language(id, deleted, created_on, last_modified, language_id, fresher_id) VALUES
(2,0,NOW(),NULL,1,2);
INSERT INTO fresher_language(id, deleted, created_on, last_modified, language_id, fresher_id) VALUES
(3,0,NOW(),NULL,2,2);
INSERT INTO fresher_language(id, deleted, created_on, last_modified, language_id, fresher_id) VALUES
(4,0,NOW(),NULL,3,3);


INSERT INTO assignment(id, deleted, created_on, last_modified, percentage, name, description) VALUES
(1,0,NOW(),NULL,30,'Assignment 1', 'First Assignment');
INSERT INTO assignment(id, deleted, created_on, last_modified, percentage, name, description) VALUES
(2,0,NOW(),NULL,30,'Assignment 2', 'Second Assignment');
INSERT INTO assignment(id, deleted, created_on, last_modified, percentage, name, description) VALUES
(3,0,NOW(),NULL,40,'Assignment 3', 'Third Assignment');
#

INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(1,0,NOW(),NULL,1,1,8);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(2,0,NOW(),NULL,2,1,9);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(3,0,NOW(),NULL,3,1,9);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(4,0,NOW(),NULL,1,2,7);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(5,0,NOW(),NULL,2,2,7);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(6,0,NOW(),NULL,3,2,10);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(7,0,NOW(),NULL,1,3,9);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(8,0,NOW(),NULL,2,3,9);
INSERT INTO assignment_score(id, deleted, created_on, last_modified, fresher_id, assignment_id, score) VALUES
(9,0,NOW(),NULL,3,3,8);
