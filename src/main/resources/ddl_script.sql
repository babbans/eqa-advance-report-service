create database eqa-apr-report-db;
use eqa-apr-report-db;
CREATE TABLE annual_program_report_setting (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    section_name VARCHAR(200) NOT NULL,
    description VARCHAR(200),
    orderValue SMALLINT,
    active BOOLEAN NOT NULL,
    academic_year YEAR,
    created_by VARCHAR(100),
    creation_datetime DATETIME,
    update_by VARCHAR(100),
    update_datetime DATETIME
);

CREATE TABLE annual_program_report_task_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    program_id BIGINT,
    department_id VARCHAR(100),
    college_id VARCHAR(100),
    academic_year YEAR,
    responsible VARCHAR(100),
    section_id BIGINT,
    active BOOLEAN NOT NULL,
    created_by VARCHAR(100),
    creation_datetime DATETIME,
    update_by VARCHAR(100),
    update_datetime DATETIME,
    FOREIGN KEY (section_id) REFERENCES annual_program_report_setting(id)
);
