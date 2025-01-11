CREATE TABLE TR_STORAGE_INFO_MANAGE (
    GROUP_ID VARCHAR(100) NOT NULL,
    STORAGE_INFO_ID bigint NOT NULL,
    STORAGE_INFO_NAME VARCHAR(100) NOT NULL,
    STORAGE_INFO_PASS VARCHAR(100) NOT NULL,
    STORAGE_INFO_MEMO VARCHAR(100),
    SOFT_DELETE_DIV CHAR(1) NOT NULL DEFAULT '0',
    CREATE_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    LAST_UPDATED_TIMESTAMP TIMESTAMP NOT NULL DEFAULT '0001-01-01 00:00:00',
    PRIMARY KEY (STORAGE_INFO_ID)
);
CREATE INDEX idx_storage_info_name ON TR_STORAGE_INFO_MANAGE (STORAGE_INFO_NAME);
CREATE SEQUENCE storage_info_id_seq INCREMENT 1 START 1 MINVALUE 1;

