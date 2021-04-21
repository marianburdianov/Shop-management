CREATE TABLE employees
(
    employee_id BIGINT NOT NULL,
    first_name  VARCHAR(30) NULL,
    last_name   VARCHAR(30) NULL,
    gender      VARCHAR(10) NULL,
    birth_date  DATE NULL,
    hire_date   DATE NULL,
    role        VARCHAR(20) NULL,
    salary      DOUBLE NULL,
    PRIMARY KEY (employee_id)
);

INSERT INTO employees
VALUES (1, 'Virat', 'Adams', 'male', '1980-12-06', '2015-10-10', 'Supervisor', 3800);
INSERT INTO employees
VALUES (2, 'Steve', 'Baker', 'male', '1985-02-25', '2011-12-22', 'Paint Specialist', 2000);
INSERT INTO employees
VALUES (3, 'Mitchel', 'Davis', 'male', '1988-01-16', '2019-05-12', 'Coordinator', 18000);
INSERT INTO employees
VALUES (4, 'Kevin', 'Frank', 'male', '1979-10-29', '2012-01-23', 'Cashier', 2300);
INSERT INTO employees
VALUES (5, 'Ricky', 'Valdo', 'male', '1981-07-31', '2017-03-08', 'Manager', 2400);