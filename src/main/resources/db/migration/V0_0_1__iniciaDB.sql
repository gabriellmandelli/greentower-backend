CREATE TABLE PERSON (
    ID UUID NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(100),
    CPF VARCHAR(11) NOT NULL,
    DATE_OF_BIRTH TIMESTAMP NOT NULL,
    NATURALNESS VARCHAR(255),
    NATIONALITY VARCHAR(255),
    GENDER VARCHAR(10),
    ADDRESS VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE AUTH_USER (
    ID UUID NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ROLE VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO AUTH_USER (ID, CREATED_AT, UPDATED_AT, NAME, EMAIL, PASSWORD, ROLE) VALUES ('775227f6-0ffe-44e1-a085-7d2fddf34f7c', '2020-08-21T23:43:00.114934', '2020-08-21T23:43:00.114934', 'GreenTower Software', 'greentower@greentower.com', '$2a$10$/Wzxz75GS865petOtlpHMOacfew3FSOj0FbzeVsGesZtwpitOC2Iu', 'ADMIN');