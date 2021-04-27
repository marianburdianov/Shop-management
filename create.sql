
    create table customers (
       customer_id bigint not null,
        address varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number bigint,
        primary key (customer_id)
    ) engine=InnoDB

    create table employees (
       employee_id bigint not null,
        birth_date date,
        first_name varchar(255),
        gender varchar(255),
        hire_date date,
        last_name varchar(255),
        role varchar(255),
        salary double precision,
        primary key (employee_id)
    ) engine=InnoDB

    create table products (
       product_id bigint not null,
        name varchar(255),
        price double precision,
        quantity integer,
        primary key (product_id)
    ) engine=InnoDB

    create table purchaseinfos (
       purchaseinfo_id bigint not null,
        amount integer,
        cost double precision,
        date date,
        customer_id bigint,
        employee_id bigint,
        product_id bigint not null,
        primary key (purchaseinfo_id)
    ) engine=InnoDB

    alter table purchaseinfos 
       add constraint FKl4snbp9qjvbed3q53wwjlu6i3 
       foreign key (customer_id) 
       references customers (customer_id)

    alter table purchaseinfos 
       add constraint FKepmmabwkkf1c9ryyxotgoi96c 
       foreign key (employee_id) 
       references employees (employee_id)

    alter table purchaseinfos 
       add constraint FK14kdnw7vwgpbbwuwtm66xvwf6 
       foreign key (product_id) 
       references products (product_id)

    alter table purchaseinfos 
       add constraint FK9wng1dub2cb3yfywm6jtugqe8 
       foreign key (purchaseinfo_id) 
       references purchaseinfos (purchaseinfo_id)

    create table customers (
       customer_id bigint not null,
        address varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number bigint,
        primary key (customer_id)
    ) engine=InnoDB

    create table employees (
       employee_id bigint not null,
        birth_date date,
        first_name varchar(255),
        gender varchar(255),
        hire_date date,
        last_name varchar(255),
        role varchar(255),
        salary double precision,
        primary key (employee_id)
    ) engine=InnoDB

    create table products (
       product_id bigint not null,
        name varchar(255),
        price double precision,
        quantity integer,
        primary key (product_id)
    ) engine=InnoDB

    create table purchaseinfos (
       purchaseinfo_id bigint not null,
        amount integer,
        cost double precision,
        date date,
        customer_id bigint,
        employee_id bigint,
        product_id bigint not null,
        primary key (purchaseinfo_id)
    ) engine=InnoDB

    alter table purchaseinfos 
       add constraint FKl4snbp9qjvbed3q53wwjlu6i3 
       foreign key (customer_id) 
       references customers (customer_id)

    alter table purchaseinfos 
       add constraint FKepmmabwkkf1c9ryyxotgoi96c 
       foreign key (employee_id) 
       references employees (employee_id)

    alter table purchaseinfos 
       add constraint FK14kdnw7vwgpbbwuwtm66xvwf6 
       foreign key (product_id) 
       references products (product_id)

    alter table purchaseinfos 
       add constraint FK9wng1dub2cb3yfywm6jtugqe8 
       foreign key (purchaseinfo_id) 
       references purchaseinfos (purchaseinfo_id)

    create table customers (
       customer_id bigint not null,
        address varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number bigint,
        primary key (customer_id)
    ) engine=InnoDB

    create table employees (
       employee_id bigint not null,
        birth_date date,
        first_name varchar(255),
        gender varchar(255),
        hire_date date,
        last_name varchar(255),
        role varchar(255),
        salary double precision,
        primary key (employee_id)
    ) engine=InnoDB

    create table products (
       product_id bigint not null,
        name varchar(255),
        price double precision,
        quantity integer,
        primary key (product_id)
    ) engine=InnoDB

    create table purchaseinfos (
       purchaseinfo_id bigint not null,
        amount integer,
        cost double precision,
        date date,
        customer_id bigint,
        employee_id bigint,
        product_id bigint not null,
        primary key (purchaseinfo_id)
    ) engine=InnoDB

    alter table purchaseinfos 
       add constraint FKl4snbp9qjvbed3q53wwjlu6i3 
       foreign key (customer_id) 
       references customers (customer_id)

    alter table purchaseinfos 
       add constraint FKepmmabwkkf1c9ryyxotgoi96c 
       foreign key (employee_id) 
       references employees (employee_id)

    alter table purchaseinfos 
       add constraint FK14kdnw7vwgpbbwuwtm66xvwf6 
       foreign key (product_id) 
       references products (product_id)

    alter table purchaseinfos 
       add constraint FK9wng1dub2cb3yfywm6jtugqe8 
       foreign key (purchaseinfo_id) 
       references purchaseinfos (purchaseinfo_id)

    create table customers (
       customer_id bigint not null,
        address varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number bigint,
        primary key (customer_id)
    ) engine=InnoDB

    create table employees (
       employee_id bigint not null,
        birth_date date,
        first_name varchar(255),
        gender varchar(255),
        hire_date date,
        last_name varchar(255),
        role varchar(255),
        salary double precision,
        primary key (employee_id)
    ) engine=InnoDB

    create table products (
       product_id bigint not null,
        name varchar(255),
        price double precision,
        quantity integer,
        primary key (product_id)
    ) engine=InnoDB

    create table purchaseinfos (
       purchaseinfo_id bigint not null,
        amount integer,
        cost double precision,
        date date,
        customer_id bigint,
        employee_id bigint,
        product_id bigint not null,
        primary key (purchaseinfo_id)
    ) engine=InnoDB

    alter table purchaseinfos 
       add constraint FKl4snbp9qjvbed3q53wwjlu6i3 
       foreign key (customer_id) 
       references customers (customer_id)

    alter table purchaseinfos 
       add constraint FKepmmabwkkf1c9ryyxotgoi96c 
       foreign key (employee_id) 
       references employees (employee_id)

    alter table purchaseinfos 
       add constraint FK14kdnw7vwgpbbwuwtm66xvwf6 
       foreign key (product_id) 
       references products (product_id)

    alter table purchaseinfos 
       add constraint FK9wng1dub2cb3yfywm6jtugqe8 
       foreign key (purchaseinfo_id) 
       references purchaseinfos (purchaseinfo_id)

    create table customers (
       customer_id bigint not null,
        address varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number bigint,
        primary key (customer_id)
    ) engine=InnoDB

    create table employees (
       employee_id bigint not null,
        birth_date date,
        first_name varchar(255),
        gender varchar(255),
        hire_date date,
        last_name varchar(255),
        role varchar(255),
        salary double precision,
        primary key (employee_id)
    ) engine=InnoDB

    create table products (
       product_id bigint not null,
        name varchar(255),
        price double precision,
        quantity integer,
        primary key (product_id)
    ) engine=InnoDB

    create table purchaseinfos (
       purchaseinfo_id bigint not null,
        amount integer,
        cost double precision,
        date date,
        customer_id bigint,
        employee_id bigint,
        product_id bigint not null,
        primary key (purchaseinfo_id)
    ) engine=InnoDB

    alter table purchaseinfos 
       add constraint FKl4snbp9qjvbed3q53wwjlu6i3 
       foreign key (customer_id) 
       references customers (customer_id)

    alter table purchaseinfos 
       add constraint FKepmmabwkkf1c9ryyxotgoi96c 
       foreign key (employee_id) 
       references employees (employee_id)

    alter table purchaseinfos 
       add constraint FK14kdnw7vwgpbbwuwtm66xvwf6 
       foreign key (product_id) 
       references products (product_id)

    alter table purchaseinfos 
       add constraint FK9wng1dub2cb3yfywm6jtugqe8 
       foreign key (purchaseinfo_id) 
       references purchaseinfos (purchaseinfo_id)
